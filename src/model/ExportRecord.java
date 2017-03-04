package model;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ExportDevice.Footer;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class ExportRecord {
	
	public static void print(ArrayList<Device> devList, ArrayList<Item> itemList, ArrayList<Model> modelList, ArrayList<Record> recList, HttpServletRequest request , HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String title = "设  备  历  史  故  障  清  单";
		String sub_title = "                  共" + recList.size() + "条记录：";
		String[] header = {"设备编号", "小项", "型号", "故障日期", "故障信息", "维修日期", "维修记录" }; 
       
        String font_hei = request.getSession().getServletContext().getRealPath("") + "/lib/simhei.ttf";
        try {		
			font_hei = java.net.URLDecoder.decode(font_hei, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		response.reset();
		response.setContentType("application/pdf");
		
		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		rectPageSize = rectPageSize.rotate();
		Document document = new Document(rectPageSize,-32,-32,50,50);
		 
		BaseFont bfChinese;
		try {
			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			Paragraph par = new Paragraph(title,new Font(bfChinese,18,Font.NORMAL));
			Paragraph sub_par = new Paragraph(sub_title,new Font(bfChinese,10,Font.NORMAL));
			par.setAlignment(Element.TITLE);
			par.setSpacingAfter(2);
			sub_par.setAlignment(Element.ALIGN_LEFT);
			sub_par.setSpacingAfter(5);

			float[] widths = {8f, 8f, 8f, 8f, 30f, 8f, 30f};
			PdfPTable table = new PdfPTable(widths);
			table.setHeaderRows(1);
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			PdfPCell cell;
			for(int k = 0; k < 7; k++){
				cell = new PdfPCell((new Paragraph(header[k],new Font(bfChinese,11,Font.NORMAL))));
				cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(21);
				cell.setBorderWidthTop(2);
				cell.setBorderWidthBottom(0);
				//cell.setBorderWidthLeft(0);
			    //cell.setBorderWidthRight(0);
				table.addCell(cell);
				
			}
			
			
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//宋体
			DecimalFormat myFormat=new DecimalFormat("00.00%"); 

			for(int k = 0; k < recList.size(); k++){
				
			    cell = new PdfPCell(new Paragraph(String.valueOf(recList.get(k).getDevice_sn()), new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    cell.setFixedHeight(15);			   
			    table.addCell(cell);
			    
			    String item = "";
			    for(int w=0; w<devList.size(); w++){
			    	for(int i=0; i<itemList.size(); i++){
				    	for(int j=0; j<modelList.size(); j++){
				    		if((recList.get(k).getDevice_sn().equals(devList.get(w).getSn()))&&(devList.get(w).getModel_sn()==modelList.get(j).getSn()) && (modelList.get(j).getItem_sn()==itemList.get(i).getSn())){
				    			item = itemList.get(i).getName();
								break;
				    		}
				    	}
				    }
			    }	
			    cell = new PdfPCell(new Paragraph(item ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(15);				
				table.addCell(cell);
			    
			    
				String model = "";
			    for(int w=0; w<devList.size(); w++){			    	
				    for(int j=0; j<modelList.size(); j++){
				    	if((recList.get(k).getDevice_sn().equals(devList.get(w).getSn()))&&(devList.get(w).getModel_sn()==modelList.get(j).getSn())){
				    		model = modelList.get(j).getName();
							break;
				    	}
				    }				    
			    }
			    cell = new PdfPCell(new Paragraph(model ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Paragraph(sdf.format(recList.get(k).getFault_date()) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(recList.get(k).getFault() ,new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_TOP);
			    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    cell.setFixedHeight((recList.get(k).getFault().length()/21)*15);			    
			    table.addCell(cell);
				
			    Date repair_date = recList.get(k).getRepair_date();
			    String date = "";
			    if(repair_date != null)
			    	date = sdf.format(repair_date);
				cell = new PdfPCell(new Paragraph(date ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(recList.get(k).getRepair() ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_TOP);
				cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
				cell.setFixedHeight((recList.get(k).getRepair().length()/21)*15);
				table.addCell(cell);									
			}
			
			cell = new PdfPCell((new Paragraph("说明：历史故障维修记录不包含当前正在维修的设备记录。 ", new Font(bfChinese,10,Font.BOLD))));
			cell.setColspan(10);			
			cell.setHorizontalAlignment(Cell.LEFT);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setFixedHeight(40);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
		    cell.setBorderWidthRight(0);
			table.addCell(cell);					
			
			
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, buffer);
			writer.setBoxSize("signature", new Rectangle(0, 0, 800, 0));//(left,bottom,right,top)
			Footer event = new Footer();
            writer.setPageEvent(event);
            
			document.open();				
			document.add(par);
			document.add(sub_par);
			document.add(table);
			document.close(); 
			
			DataOutput output = new DataOutputStream(response.getOutputStream());
			byte[] bytes = buffer.toByteArray();
			response.setContentLength(bytes.length);
			for(int i=0;i<bytes.length;i++){
			    output.writeByte(bytes[i]);			  
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	public static void print(ArrayList<Keeper> keeperList, ArrayList<Device> devList, ArrayList<Item> itemList, ArrayList<Model> modelList, ArrayList<Record> recList, HttpServletRequest request , HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String title = "设  备  历  史  故  障  清  单";
		String sub_title = "                  共" + recList.size() + "条记录：";
		String[] header = {"设备编号", "小项", "型号", "故障日期", "故障信息", "维修日期", "维修记录", "保管人"}; 
       
        String font_hei = request.getSession().getServletContext().getRealPath("") + "/lib/simhei.ttf";
        try {		
			font_hei = java.net.URLDecoder.decode(font_hei, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		response.reset();
		response.setContentType("application/pdf");
		
		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		rectPageSize = rectPageSize.rotate();
		Document document = new Document(rectPageSize,-32,-32,50,50);
		 
		BaseFont bfChinese;
		try {
			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			Paragraph par = new Paragraph(title,new Font(bfChinese,18,Font.NORMAL));
			Paragraph sub_par = new Paragraph(sub_title,new Font(bfChinese,10,Font.NORMAL));
			par.setAlignment(Element.TITLE);
			par.setSpacingAfter(2);
			sub_par.setAlignment(Element.ALIGN_LEFT);
			sub_par.setSpacingAfter(5);

			float[] widths = {8f, 8f, 8f, 8f, 26f, 8f, 26f, 8f};
			PdfPTable table = new PdfPTable(widths);
			table.setHeaderRows(1);
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			PdfPCell cell;
			for(int k = 0; k < 8; k++){
				cell = new PdfPCell((new Paragraph(header[k],new Font(bfChinese,11,Font.NORMAL))));
				cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(21);
				cell.setBorderWidthTop(2);
				cell.setBorderWidthBottom(0);
				//cell.setBorderWidthLeft(0);
			    //cell.setBorderWidthRight(0);
				table.addCell(cell);
				
			}
			
			
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//宋体
			DecimalFormat myFormat=new DecimalFormat("00.00%"); 

			for(int k = 0; k < recList.size(); k++){
				
			    cell = new PdfPCell(new Paragraph(String.valueOf(recList.get(k).getDevice_sn()), new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    cell.setFixedHeight(15);			   
			    table.addCell(cell);
			    
			    String item = "";
			    for(int w=0; w<devList.size(); w++){
			    	for(int i=0; i<itemList.size(); i++){
				    	for(int j=0; j<modelList.size(); j++){
				    		if((recList.get(k).getDevice_sn().equals(devList.get(w).getSn()))&&(devList.get(w).getModel_sn()==modelList.get(j).getSn()) && (modelList.get(j).getItem_sn()==itemList.get(i).getSn())){
				    			item = itemList.get(i).getName();
								break;
				    		}
				    	}
				    }
			    }	
			    cell = new PdfPCell(new Paragraph(item ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(15);				
				table.addCell(cell);
			    
			    
				String model = "";
			    for(int w=0; w<devList.size(); w++){			    	
				    for(int j=0; j<modelList.size(); j++){
				    	if((recList.get(k).getDevice_sn().equals(devList.get(w).getSn()))&&(devList.get(w).getModel_sn()==modelList.get(j).getSn())){
				    		model = modelList.get(j).getName();
							break;
				    	}
				    }				    
			    }
			    cell = new PdfPCell(new Paragraph(model ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Paragraph(sdf.format(recList.get(k).getFault_date()) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(recList.get(k).getFault() ,new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_TOP);
			    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    cell.setFixedHeight((recList.get(k).getFault().length()/18)*15);			    
			    table.addCell(cell);
				
			    Date repair_date = recList.get(k).getRepair_date();
			    String date = "";
			    if(repair_date != null)
			    	date = sdf.format(repair_date);
				cell = new PdfPCell(new Paragraph(date ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(recList.get(k).getRepair() ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_TOP);
				cell.setHorizontalAlignment(Cell.ALIGN_LEFT);				
				cell.setFixedHeight((recList.get(k).getRepair().length()/18)*15);
				table.addCell(cell);
				
				String keeper = "";
				for(int i=0; i<keeperList.size(); i++){
					for(int j=0; j<devList.size(); j++){
					    if(recList.get(k).getDevice_sn().equals(devList.get(j).getSn())&&(keeperList.get(i).getSn().equals(devList.get(j).getKeeper()))){
					    	keeper = keeperList.get(i).getName();
					    	break;
					    }
					}
				}
				cell = new PdfPCell(new Paragraph(keeper ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);
				table.addCell(cell);
			}
			
			cell = new PdfPCell((new Paragraph("说明：历史故障维修记录不包含当前正在维修的设备记录。 ", new Font(bfChinese,10,Font.BOLD))));
			cell.setColspan(10);			
			cell.setHorizontalAlignment(Cell.LEFT);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setFixedHeight(40);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
		    cell.setBorderWidthRight(0);
			table.addCell(cell);					
			
			
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, buffer);
			writer.setBoxSize("signature", new Rectangle(0, 0, 800, 0));//(left,bottom,right,top)
			Footer event = new Footer();
            writer.setPageEvent(event);
            
			document.open();				
			document.add(par);
			document.add(sub_par);
			document.add(table);
			document.close(); 
			
			DataOutput output = new DataOutputStream(response.getOutputStream());
			byte[] bytes = buffer.toByteArray();
			response.setContentLength(bytes.length);
			for(int i=0;i<bytes.length;i++){
			    output.writeByte(bytes[i]);			  
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	
	
	
	public static void printSingle(ArrayList<Device> devList, ArrayList<Item> itemList, ArrayList<Model> modelList, ArrayList<Record> recList, ArrayList<Supplier> supplierList,HttpServletRequest request , HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String title = "设  备  历  史  故  障  清  单";
		String inf1 = "             设备编号：" + request.getParameter("sn") + "         ";
		String inf2 = "             ";
		String sub_title = "             共" + recList.size() + "条记录：";
		String[] header = {"故障日期", "故障信息", "维修日期", "维修记录" }; 
		
		String item = "";
	    for(int w=0; w<devList.size(); w++){
	    	for(int i=0; i<itemList.size(); i++){
		    	for(int j=0; j<modelList.size(); j++){
		    		if((devList.get(w).getSn().equals(request.getParameter("sn"))) && (devList.get(w).getModel_sn()==modelList.get(j).getSn()) && (modelList.get(j).getItem_sn()==itemList.get(i).getSn())){
		    			item = itemList.get(i).getName();
						break;
		    		}
		    	}
		    }
	    }
	    inf1 += ("小项名称：" + item) + "         ";
	    
	    String model = "";
	    int life = 0;
	    String appendix = "N";
	  
	    for(int w=0; w<devList.size(); w++){	    	
		    for(int j=0; j<modelList.size(); j++){
		    	if((devList.get(w).getSn().equals(request.getParameter("sn"))) && (devList.get(w).getModel_sn()==modelList.get(j).getSn()) ){
		    		model = modelList.get(j).getName();
		    		life = modelList.get(j).getLife();
		    		appendix = modelList.get(j).getHasAppendix();
					break;
		    	}
		    }		   
	    }	
	    inf1 += ("型号名称：" + model) + "         ";
	    inf1 += ("使用年限：" + life);
	    
	    String supplier = "";
	    for(int w=0; w<devList.size(); w++){
	    	for(int i=0; i<supplierList.size(); i++){
		    	for(int j=0; j<modelList.size(); j++){
		    		if((devList.get(w).getSn().equals(request.getParameter("sn"))) && (devList.get(w).getModel_sn()==modelList.get(j).getSn()) && (modelList.get(j).getSupplier_sn()==supplierList.get(i).getSn())){
		    			supplier = supplierList.get(i).getName();
						break;
		    		}
		    	}
		    }
	    }
	    inf2 += ("生产厂家：" + supplier + "        ");
	    if(appendix.equals("N"))
	    	inf2 += ("是否有配件：无") + "   ";
	    else
	    	inf2 += ("是否有配件：有") + "   ";
	    
	    
	    
        String font_hei = request.getSession().getServletContext().getRealPath("") + "/lib/simhei.ttf";
        try {		
			font_hei = java.net.URLDecoder.decode(font_hei, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		response.reset();
		response.setContentType("application/pdf");
		
		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		//rectPageSize = rectPageSize.rotate();
		Document document = new Document(rectPageSize,-32,-32,50,50);
		 
		BaseFont bfChinese;
		try {
			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			Paragraph dev_inf1 = new Paragraph(inf1,new Font(bfChinese,10,Font.NORMAL));
			Paragraph dev_inf2 = new Paragraph(inf2,new Font(bfChinese,10,Font.NORMAL));
			dev_inf2.setSpacingAfter(10);
			Paragraph par = new Paragraph(title,new Font(bfChinese,18,Font.NORMAL));
			Paragraph sub_par = new Paragraph(sub_title,new Font(bfChinese,10,Font.NORMAL));
			par.setAlignment(Element.TITLE);
			par.setSpacingAfter(15);
			sub_par.setAlignment(Element.ALIGN_LEFT);
			sub_par.setSpacingAfter(5);

			float[] widths = {10f, 40f, 10f, 40f};
			PdfPTable table = new PdfPTable(widths);
			table.setHeaderRows(1);
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			PdfPCell cell;
			for(int k = 0; k < 4; k++){
				cell = new PdfPCell((new Paragraph(header[k],new Font(bfChinese,11,Font.NORMAL))));
				cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(21);
				cell.setBorderWidthTop(2);
				cell.setBorderWidthBottom(0);
				table.addCell(cell);
				
			}
			
			
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//宋体
			//DecimalFormat myFormat=new DecimalFormat("00.00%"); 

			for(int k = 0; k < recList.size(); k++){

				cell = new PdfPCell(new Paragraph(sdf.format(recList.get(k).getFault_date()) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(recList.get(k).getFault() ,new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_TOP);
			    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    cell.setFixedHeight((recList.get(k).getFault().length()/20)*15);			    
			    table.addCell(cell);
				
			    Date repair_date = recList.get(k).getRepair_date();
			    String date = "";
			    if(repair_date != null)
			    	date = sdf.format(repair_date);
				cell = new PdfPCell(new Paragraph(date ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(recList.get(k).getRepair() ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_TOP);
				cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
				cell.setFixedHeight((recList.get(k).getRepair().length()/20)*15);
				table.addCell(cell);									
			}
			
			cell = new PdfPCell((new Paragraph("说明：历史故障维修记录不包含当前正在维修的该设备记录。 ", new Font(bfChinese,10,Font.BOLD))));
			cell.setColspan(10);			
			cell.setHorizontalAlignment(Cell.LEFT);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setFixedHeight(40);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
		    cell.setBorderWidthRight(0);
			table.addCell(cell);					
			
			
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, buffer);
			writer.setBoxSize("signature", new Rectangle(0, 0, 580, 0));//(left,bottom,right,top)
			Footer event = new Footer();
            writer.setPageEvent(event);
            
			document.open();				
			document.add(par);
			document.add(dev_inf1);
			document.add(dev_inf2);
			document.add(sub_par);
			document.add(table);
			document.close(); 
			
			DataOutput output = new DataOutputStream(response.getOutputStream());
			byte[] bytes = buffer.toByteArray();
			response.setContentLength(bytes.length);
			for(int i=0;i<bytes.length;i++){
			    output.writeByte(bytes[i]);			  
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
static class Footer extends PdfPageEventHelper {
		
		public PdfTemplate template;   
	    public BaseFont bfChinese; 
		
		public void onOpenDocument(PdfWriter writer, Document document) {   
	        try {   
	            template = writer.getDirectContent().createTemplate(100, 100);   
	            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
	            			
	        }   
	        catch(Exception e) {   
	            throw new ExceptionConverter(e);   
	        }   
	    }   
		
		public void onCloseDocument(PdfWriter writer, Document document) {   
		       //关闭document的时候获取总页数，并把总页数按模版写道之前预留的位置   
			template.beginText();   			
		    template.setFontAndSize(bfChinese, 12);		
			template.showText(String.valueOf(writer.getPageNumber() - 1));   
			template.endText();   
			template.closePath();//sanityCheck();  
			
			
	    } 
		
		

		public void onEndPage (PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("signature");
			try {
				
				bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				
				
				
				String date_str = "";
			    Date dt = new Date();		    
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
			    date_str = sdf.format(dt);
				ColumnText.showTextAligned(writer.getDirectContent(), 
		                   Element.ALIGN_RIGHT, new Paragraph(date_str, new Font(bfChinese,13,Font.NORMAL)),
		                    rect.getRight()-18, rect.getBottom() + 35, 0);
				ColumnText.showTextAligned(writer.getDirectContent(),
	                    Element.ALIGN_CENTER, new Paragraph(("页码：" + writer.getCurrentPageNumber() + " /"), new Font(bfChinese, 12, Font.COURIER)),
	                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() + 18, 0);
				PdfContentByte cb = writer.getDirectContent(); 
				cb.addTemplate(template, (rect.getLeft() + rect.getRight()) / 2 + 28, rect.getBottom() + 18);
			
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
        }
    }

}
