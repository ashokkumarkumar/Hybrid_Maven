package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.bcel.generic.LAND;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	
//constructor for reading excel path
	public ExcelFileUtil(String excelpath)throws Throwable
	{
		FileInputStream fi =  new FileInputStream(excelpath);
		wb = new XSSFWorkbook(fi);
	}
//count no of rows in a sheet
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
//get cell type data
	public String getCellData(String SheetName,int row,int column)
	{
		String data="";
		if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata =(int) wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
			data =String.valueOf(celldata);
		}
		else
		{
			data =wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();	
		}
		return data;
	}
//method for set cell data
	public void setCellData(String sheetName,int row,int column,String status,String writeexcel)throws Throwable
	{
//get sheet from wb
		XSSFSheet ws = wb.getSheet(sheetName);
//get row from sheet
		XSSFRow rowNum =ws.getRow(row);
//create cell in row
		XSSFCell cell =rowNum.createCell(column);
//write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeexcel);
		wb.write(fo);

	}
	
public static void main(String[] args) throws Throwable{
		ExcelFileUtil xl = new ExcelFileUtil("D:/DataDrivenSample.xlsx");
//count noo f rows 
		int rc =xl.rowCount("EmpData");
		System.out.println(rc);
		
		for(int i=1;i<=rc;i++)
		{
			String fname =xl.getCellData("EmpData", i, 0);
			String mname = xl.getCellData("EmpData", i, 1);
			String lname = xl.getCellData("EmpData", i, 2);
			String eid = xl.getCellData("EmpData", i, 3);
			System.out.println(fname+"   "+mname+"    "+lname+"    "+eid);
			xl.setCellData("EmpData", i, 4, "Pass", "D://DataDrivenResults.xlsx");
			//xl.setCellData("EmpData", i, 4, "Fail", "D://DataDrivenResults.xlsx");
			//xl.setCellData("EmpData", i, 4, "Blocked", "D://DataDrivenResults.xlsx");

		}

	}

}