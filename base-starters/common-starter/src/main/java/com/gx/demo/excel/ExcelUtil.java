package com.gx.demo.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName : ExcelUtil
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/30 14:06
 * @Version : 1.0
 */
public class ExcelUtil {

    /**
     * 导出缓冲
     */
    private static final Integer MAX_WRItE_ROW_ACCESS = 1000;

    public ExcelUtil(){}

    /**
     * 创建读取
     * @return
     */
    public static ExcelReader createExcelReader(){
        return new ExcelReader();
    }

    /**
     * excel 读取
     */
    public static class ExcelReader{

        private File file;
        private FileInputStream fileInputStream;
        private String fileName;
        /**
         * 需要读取的 sheet 名称
         */
        private List<String> sheets = new LinkedList<>();
        /**
         * 从第几行 开始读取
         */
        private Integer startRowNum = -1;
        private List<Integer> selectColumnIndex;

        public ExcelReader(){}

        public ExcelReader setFileName (String fileName){
            this.fileName = fileName;
            if(Objects.isNull(fileInputStream)){
                this.file = new File(fileName);
            }
            return this;
        }

        /**
         * 设置文件名称
         * @return
         */
        public String getFileName(){
            return Optional.ofNullable(fileName).orElse("");
        }

        public ExcelReader setFile (File file){
            this.file = file;
            return this;
        }

        public ExcelReader setFileInputStream(FileInputStream fileInputStream){
            this.fileInputStream = fileInputStream;
            return this;
        }

        public InputStream getFileInputStream() throws FileNotFoundException {
            return Objects.isNull(fileInputStream) ? new FileInputStream(file) : fileInputStream;
        }

        public ExcelReader addSheet(String sheetName){
            this.sheets.add(sheetName);
            return this;
        }

        public ExcelReader addSheet(String ... sheetNames){
            return addSheet(Stream.of(sheetNames).collect(Collectors.toList()));
        }

        public ExcelReader addSheet(List<String> sheetNameList){
            this.sheets.addAll(sheetNameList);
            return this;
        }

        public ExcelReader skipFirstLine(){
            this.startRowNum = 1;
            return this;
        }

        /**
         * 按行读取
         * @param readCellFunction
         * @param <R>
         * @return
         */
        public <R> Map<String,List<List<R>>> withCell(BiFunction<Cell,FormulaEvaluator,R> readCellFunction){
            Map<String,List<List<R>>> resMap = Maps.newLinkedHashMap();
            String fileName = this.fileName;
            //获取工作
            try{
                InputStream inputStream = getFileInputStream();

                Workbook wb = WorkbookFactory.create(inputStream);
                //格式化值
                FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
                //遍历 sheet
                Iterator<Sheet> sheetIterator = wb.sheetIterator();
                while (sheetIterator.hasNext()){
                    Sheet sheet = sheetIterator.next();
                    //如果不存在于 需要读取的 sheet，则跳过
                    if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(sheets) && !sheets.contains(sheet.getSheetName())) {
                        continue;
                    }
                    //读取行
                    Iterator<Row> rowIterator = sheet.rowIterator();
                    List<List<R>> sheetDatas = Lists.newLinkedList();
                    while(rowIterator.hasNext()){
                        Row row = rowIterator.next();
                        int rowIndexNum = row.getRowNum();
                        // 跳过行
                        if(rowIndexNum < startRowNum){
                            continue;
                        }

                        List<R> rowDatas = Lists.newLinkedList();
                        for(int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++){
                            Cell cell = row.getCell(cellIndex);
                            R cellData = readCellFunction.apply(cell,formulaEvaluator);
                            // 根据 cell 跳过
                            rowDatas.add(cellData);
                        }
                        sheetDatas.add(rowDatas);
                    }
                    resMap.put(sheet.getSheetName(),sheetDatas);
                }



            }catch (FileNotFoundException nfe){
                nfe.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resMap;
        }

        /**
         * 读取数据
         * @return
         */
        public Map<String,List<List<String>>> read(){return withCell(ExcelUtil::getCellValue);}
    }

    /**
     * 格式化读取值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell,FormulaEvaluator formulaEvaluator){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                CellValue cellValueFormula = formulaEvaluator.evaluate(cell);
                CellType formulaCellType = cellValueFormula.getCellType();
                switch (formulaCellType){
                    case BLANK:
                        cellValue = "";
                        break;
                    case ERROR:
                        cellValue = "非法字符";
                        break;
                    case STRING:
                        cellValue = String.valueOf(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                        cellValue = String.valueOf(cell.getNumericCellValue());
                        break;
                    default:
                        cellValue = "未知类型";
                        break;
                }
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            case ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


    /**
     * 创建输出
     * @return
     */
    public static ExcelWriter createExcelWriter(){
        return new ExcelWriter();
    }

    /**
     * excel 输出
     */
    public static class ExcelWriter{

        private  String fileName;

        private OutputStream outputStream;

        private ExcelDTO allDatas;


        public ExcelWriter setFileName ( String fileName){
            this.fileName = fileName;
            return this;
        }

        public ExcelWriter setOutputStream ( OutputStream outputStream){
            this.outputStream = outputStream;
            return this;
        }

        public ExcelWriter setDatas(ExcelDTO excelDTO){
            this.allDatas = excelDTO;
            return this;
        }

        public void writeToSheet(Workbook wb,String sheetName,List<List<String>> data){
            Sheet sheet = wb.createSheet(sheetName);
            writeRow(sheet,data);
        }

        public void writeRow(Sheet sheet,List<List<String>> data){
            for(int i = 0 ; i < data.size(); i++){
                Row row = sheet.createRow(i);
                writeCell(row,data.get(i));
            }
        }

        public void writeCell(Row row,List<String> data){
            for (int i = 0; i < data.size(); i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(data.get(i));
            }
        }

        //todo 待完善
        public void write(){
            try {
            //获取工作薄
            Workbook workbook = getWorkBook(this.fileName);
            //获取 sheet 和 头部
            Map<String,List<String>> sheetsAndTitles = allDatas.getSheets();
            Map<String,List<List<String>>> sheetsAndDatas = allDatas.getDatas();
            //写数据
            for(String sheetName : sheetsAndTitles.keySet()){
                List<List<String>> data = Lists.newLinkedList();
                data.add(sheetsAndTitles.get(sheetName));
                data.addAll(sheetsAndDatas.get(sheetName));
                writeToSheet(workbook,sheetName,data);
            }

            workbook.write(outputStream);
            workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }

    /**
     * 获取工作薄
     * @param fileName
     * @return
     */
    public static Workbook getWorkBook( String fileName){
        Workbook wb = null;
        if(StringUtils.isBlank(fileName)){
            return wb;
        }
        ExcelTypeEnum typeEnum = ExcelTypeEnum.getByFileName(fileName);
        switch (typeEnum){
            case XLS:
                wb = new HSSFWorkbook();
                break;
            case XLSX:
                SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(MAX_WRItE_ROW_ACCESS);
                sxssfWorkbook.setCompressTempFiles(true);
                wb = sxssfWorkbook;
                break;
            default:
                break;
        }
        return wb;
    }

}
