package utility;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtils 
{
	public WritableWorkbook objwbk;
	public WritableSheet objsht;
	public Workbook wbk;
	public Sheet sht;
	public Cell objcell;
	public Label label; 
	
	public void setExcelFile(String Path,String SheetName) throws BiffException, IOException
	{
		wbk = Workbook.getWorkbook(new File(Path));
		sht = wbk.getSheet(SheetName);
	}
	
	public String getCellData(int Row, int Col)
	{
		objcell = sht.getCell(Col,Row);  // getcell is always [col,row] in function  and excel data starts from [0,0]
		String Celldata = objcell.getContents();
		return Celldata;
	
	}	
	public int getRowCount(File Path,String SheetName) throws Exception, IOException
	{
		wbk = Workbook.getWorkbook(Path);
		sht = wbk.getSheet(SheetName);
		int rowcount = sht.getRows();
		return rowcount;
	}
	public int getColumnCount(File Path,String SheetName) throws Exception, IOException
	{
		wbk = Workbook.getWorkbook(Path);
		sht = wbk.getSheet(SheetName);
		int Columncount = sht.getColumns();
		return Columncount;
	}
	public void WriteExcelFile(int row, int col, String value, File Path,String SheetName) throws Exception
	{
		wbk = Workbook.getWorkbook(Path);
		sht = wbk.getSheet(SheetName);
		objwbk = Workbook.createWorkbook(new File("D:/ashu/Automation/mavenartid/Output/ExcelOutput/OutputResult.xls"),wbk);
		objsht = objwbk.getSheet(0);
		label= new Label(row, col, value);
		objsht.addCell(label);
		objwbk.write();
		objwbk.close();
	}
}
