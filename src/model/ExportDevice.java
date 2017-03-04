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

public class ExportDevice {
	
	public static void print(ArrayList<Device> devList, ArrayList<Item> itemList, ArrayList<Model> modelList, ArrayList<Location> locList, ArrayList<Keeper> keeper_list, String keeper, HttpServletRequest request , HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String title = "设  备  信  息  清  单";
		String sub_title = "             共" + devList.size() + "条记录：";
		String[] header = {"设备编号", "小项", "型号", "购买日期", "状况", "带电", "存放位置", "保管人" }; 
        boolean isNull = true;
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
			Paragraph par = new Paragraph(title,new Font(bfChinese,18,Font.NORMAL));
			Paragraph sub_par = new Paragraph(sub_title,new Font(bfChinese,10,Font.NORMAL));
			par.setAlignment(Element.TITLE);
			par.setSpacingAfter(2);
			sub_par.setAlignment(Element.ALIGN_LEFT);
			sub_par.setSpacingAfter(5);

			float[] widths = {11f, 11f, 11f, 11f, 5f, 5f, 22f, 7f};
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

			for(int k = 0; k < devList.size(); k++){
				
			    cell = new PdfPCell(new Paragraph(String.valueOf(devList.get(k).getSn()), new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    cell.setFixedHeight(15);
			   
			    table.addCell(cell);
			    
			    for(int i=0; i<itemList.size(); i++){
			    	for(int j=0; j<modelList.size(); j++){
			    		if((devList.get(k).getModel_sn()==modelList.get(j).getSn()) && (modelList.get(j).getItem_sn()==itemList.get(i).getSn())){
			    			cell = new PdfPCell(new Paragraph(itemList.get(i).getName() ,new Font(bfChinese,10,Font.NORMAL)));
							cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
							cell.setFixedHeight(15);
							
							table.addCell(cell);
							
			    		}
			    	}
			    }
			    
			    
			    for(int j=0; j<modelList.size(); j++){
		    		if(devList.get(k).getModel_sn()==modelList.get(j).getSn()){
		    			cell = new PdfPCell(new Paragraph(modelList.get(j).getName() ,new Font(bfChinese,10,Font.NORMAL)));
						cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
						cell.setFixedHeight(15);
						
						table.addCell(cell);
		    		}
		    	}	
			    
				
				
				cell = new PdfPCell(new Paragraph(sdf.format(devList.get(k).getBuy_date()) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);
				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(devList.get(k).getStatus() ,new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			    cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			    cell.setFixedHeight(15);
			    
			    table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(devList.get(k).getIsElectric() ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);
				
				table.addCell(cell);
				
				for(int i=0; i<locList.size(); i++){
					if(locList.get(i).getSn()==devList.get(k).getPosition()){
						cell = new PdfPCell(new Paragraph(locList.get(i).getAddress() ,new Font(bfChinese,10,Font.NORMAL)));
						cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
						cell.setFixedHeight(15);
						
						table.addCell(cell);
						isNull = false;
					}
				}
				
				if(isNull){
			    	cell = new PdfPCell(new Paragraph("" ,new Font(bfChinese,10,Font.NORMAL)));
					cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
					cell.setFixedHeight(15);
					
					table.addCell(cell);
			    }
				isNull = true;
				
				String keeper_name = "";
				if(keeper_list == null)
				    keeper_name = keeper;
				else{
					for(int i=0; i<keeper_list.size(); i++){
						if(keeper_list.get(i).getSn().equals(devList.get(k).getKeeper())){
							keeper_name = keeper_list.get(i).getName();
							break;
						}
					}
				}
				cell = new PdfPCell(new Paragraph(keeper_name ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);
				
				table.addCell(cell);
									
			}
			
			cell = new PdfPCell((new Paragraph("说明：G---良好；F---故障；S---报废；Y---长期带电；N---非长期带电 ", new Font(bfChinese,10,Font.BOLD))));
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
	
	
	public static void printAlarm(ArrayList<Device> devList, ArrayList<Item> itemList, ArrayList<Model> modelList, ArrayList<Location> locList, ArrayList<Keeper> keeper_list, String keeper, HttpServletRequest request , HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String title = "设  备  寿  命  报  警  信  息";
		String sub_title = "             共" + devList.size() + "条记录：";
		String[] header = {"设备编号", "小项", "型号", "购买日期", "使用年限", "距报废", "存放位置", "保管人" }; 
        
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
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df = new DecimalFormat("0.0");
        	
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			Paragraph par = new Paragraph(title,new Font(bfChinese,18,Font.NORMAL));
			Paragraph sub_par = new Paragraph(sub_title,new Font(bfChinese,10,Font.NORMAL));
			par.setAlignment(Element.TITLE);
			par.setSpacingAfter(2);
			sub_par.setAlignment(Element.ALIGN_LEFT);
			sub_par.setSpacingAfter(5);

			float[] widths = {10f, 10f, 10f, 10f, 8f, 8f, 20f, 7f};
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

			for(int k = 0; k < devList.size(); k++){
				
			    cell = new PdfPCell(new Paragraph(String.valueOf(devList.get(k).getSn()), new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    cell.setFixedHeight(15);
			   
			    table.addCell(cell);
			    
			    for(int i=0; i<itemList.size(); i++){
			    	for(int j=0; j<modelList.size(); j++){
			    		if((devList.get(k).getModel_sn()==modelList.get(j).getSn()) && (modelList.get(j).getItem_sn()==itemList.get(i).getSn())){
			    			cell = new PdfPCell(new Paragraph(itemList.get(i).getName() ,new Font(bfChinese,10,Font.NORMAL)));
							cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
							cell.setFixedHeight(15);
							
							table.addCell(cell);
							
			    		}
			    	}
			    }
			    
			    
			    for(int j=0; j<modelList.size(); j++){
		    		if(devList.get(k).getModel_sn()==modelList.get(j).getSn()){
		    			cell = new PdfPCell(new Paragraph(modelList.get(j).getName() ,new Font(bfChinese,10,Font.NORMAL)));
						cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
						cell.setFixedHeight(15);
						
						table.addCell(cell);
		    		}
		    	}	
			    						
				cell = new PdfPCell(new Paragraph(sdf.format(devList.get(k).getBuy_date()) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				
			    int life = 0;
			    for(int j=0; j<modelList.size(); j++){
		    		if(devList.get(k).getModel_sn()==modelList.get(j).getSn()){
		    			life = modelList.get(j).getLife();
		    			break;
		    		}
		    	}
			    
			    cell = new PdfPCell(new Paragraph(String.valueOf(life) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
                
				cell = new PdfPCell(new Paragraph(df.format(life - devList.get(k).getElapsed_year()) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				String loc = "";
				for(int i=0; i<locList.size(); i++){
					if(locList.get(i).getSn()==devList.get(k).getPosition()){
						loc = locList.get(i).getAddress();	
						break;
					}
				}
			    cell = new PdfPCell(new Paragraph(loc ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(15);					
				table.addCell(cell);
			    
				String keeper_name = "";
				if(keeper_list == null)
				    keeper_name = keeper;
				else{
					for(int i=0; i<keeper_list.size(); i++){
						if(keeper_list.get(i).getSn().equals(devList.get(k).getKeeper())){
							keeper_name = keeper_list.get(i).getName();
							break;
						}
					}
				}
				cell = new PdfPCell(new Paragraph(keeper_name ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
									
			}
			
			cell = new PdfPCell((new Paragraph("说明：所有数字以(年)为单位。 ", new Font(bfChinese,10,Font.BOLD))));
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
	
	public static void print(ArrayList<Device> devList, ArrayList<Item> itemList, ArrayList<Model> modelList, ArrayList<Keeper> keeper_list, String keeper, HttpServletRequest request , HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String title = "设  备  使  用  年  限  信  息";
		String sub_title = "             共" + devList.size() + "条记录：";
		String[] header = {"设备编号", "小项", "型号", "购买日期", "使用年数", "距报废时间", "使用年限", "保管人" }; 
        
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
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df = new DecimalFormat("0.0");
        	
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			Paragraph par = new Paragraph(title,new Font(bfChinese,18,Font.NORMAL));
			Paragraph sub_par = new Paragraph(sub_title,new Font(bfChinese,10,Font.NORMAL));
			par.setAlignment(Element.TITLE);
			par.setSpacingAfter(2);
			sub_par.setAlignment(Element.ALIGN_LEFT);
			sub_par.setSpacingAfter(5);

			float[] widths = {11f, 11f, 11f, 11f, 10f, 12f, 10f, 7f};
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

			for(int k = 0; k < devList.size(); k++){
				
			    cell = new PdfPCell(new Paragraph(String.valueOf(devList.get(k).getSn()), new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    cell.setFixedHeight(15);
			   
			    table.addCell(cell);
			    
			    for(int i=0; i<itemList.size(); i++){
			    	for(int j=0; j<modelList.size(); j++){
			    		if((devList.get(k).getModel_sn()==modelList.get(j).getSn()) && (modelList.get(j).getItem_sn()==itemList.get(i).getSn())){
			    			cell = new PdfPCell(new Paragraph(itemList.get(i).getName() ,new Font(bfChinese,10,Font.NORMAL)));
							cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
							cell.setFixedHeight(15);
							
							table.addCell(cell);
							
			    		}
			    	}
			    }
			    
			    
			    for(int j=0; j<modelList.size(); j++){
		    		if(devList.get(k).getModel_sn()==modelList.get(j).getSn()){
		    			cell = new PdfPCell(new Paragraph(modelList.get(j).getName() ,new Font(bfChinese,10,Font.NORMAL)));
						cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
						cell.setFixedHeight(15);
						
						table.addCell(cell);
		    		}
		    	}	
			    						
				cell = new PdfPCell(new Paragraph(sdf.format(devList.get(k).getBuy_date()) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(String.valueOf(devList.get(k).getElapsed_year()) ,new Font(bfChinese,10,Font.NORMAL)));
			    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			    cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			    cell.setFixedHeight(15);			    
			    table.addCell(cell);
				
			    int life = 0;
			    for(int j=0; j<modelList.size(); j++){
		    		if(devList.get(k).getModel_sn()==modelList.get(j).getSn()){
		    			life = modelList.get(j).getLife();
		    			break;
		    		}
		    	}
			    
				cell = new PdfPCell(new Paragraph(df.format(life - devList.get(k).getElapsed_year()) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(String.valueOf(life) ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
				
				String keeper_name = "";
				if(keeper_list == null)
				    keeper_name = keeper;
				else{
					for(int i=0; i<keeper_list.size(); i++){
						if(keeper_list.get(i).getSn().equals(devList.get(k).getKeeper())){
							keeper_name = keeper_list.get(i).getName();
							break;
						}
					}
				}
				cell = new PdfPCell(new Paragraph(keeper_name ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setFixedHeight(15);				
				table.addCell(cell);
									
			}
			
			cell = new PdfPCell((new Paragraph("说明：所有数字以(年)为单位。 ", new Font(bfChinese,10,Font.BOLD))));
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
	
	
	public static void print(ArrayList<Device> devList, ArrayList<Item> itemList, ArrayList<Model> modelList, ArrayList<Location> locList, ArrayList<Keeper> keeper_list, String keeper, String dev_sn, HttpServletRequest request , HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String title = "单 台 设 备 设 施 详 细 信 息";
		String[] header = {"设备编号", "小项", "购买日期", "型号", "生产厂家", "有无备件"}; 
		String[] header2 = {"长期带电", "状况", "使用年限", "已使用", "距报废", "存放位置", "保管人"};
        boolean isNull = true;
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
		
		int k = -1;
		for(int i = 0; i < devList.size(); i++){
			if(devList.get(i).getSn().equals(dev_sn)){
				k = i;
			}
		}
		
		try {
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df = new DecimalFormat("0.0");
			
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			Paragraph par = new Paragraph(title,new Font(bfChinese,18,Font.NORMAL));
			par.setAlignment(Element.TITLE);
			par.setSpacingAfter(30);

			float[] widths = {15f, 15f, 15f, 15f, 28f, 12f};
			PdfPTable table = new PdfPTable(widths);
			table.setHeaderRows(1);
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			PdfPCell cell;
			for(int i = 0; i < 6; i++){
				cell = new PdfPCell((new Paragraph(header[i],new Font(bfChinese,11,Font.NORMAL))));
				cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(21);
				cell.setBorderWidthTop(2);
				cell.setBorderWidthBottom(0);
				table.addCell(cell);
				
			}
			
			
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//宋体
			DecimalFormat myFormat=new DecimalFormat("00.00%"); 

			cell = new PdfPCell(new Paragraph(String.valueOf(devList.get(k).getSn()), new Font(bfChinese,10,Font.NORMAL)));
		    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		    cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
		    cell.setFixedHeight(15);
		   
		    table.addCell(cell);
		    
		    for(int i=0; i<itemList.size(); i++){
		    	for(int j=0; j<modelList.size(); j++){
		    		if((devList.get(k).getModel_sn()==modelList.get(j).getSn()) && (modelList.get(j).getItem_sn()==itemList.get(i).getSn())){
		    			cell = new PdfPCell(new Paragraph(itemList.get(i).getName() ,new Font(bfChinese,10,Font.NORMAL)));
						cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
						cell.setFixedHeight(15);
						
						table.addCell(cell);
						
		    		}
		    	}
		    }			    

			cell = new PdfPCell(new Paragraph(sdf.format(devList.get(k).getBuy_date()) ,new Font(bfChinese,10,Font.NORMAL)));
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			cell.setFixedHeight(15);
			table.addCell(cell);
		    
		    
		    for(int j=0; j<modelList.size(); j++){
	    		if(devList.get(k).getModel_sn()==modelList.get(j).getSn()){
	    			cell = new PdfPCell(new Paragraph(modelList.get(j).getName() ,new Font(bfChinese,10,Font.NORMAL)));
					cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
					cell.setFixedHeight(15);
					table.addCell(cell);							

					cell = new PdfPCell(new Paragraph(modelList.get(j).getSupplier() ,new Font(bfChinese,10,Font.NORMAL)));
					cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
					cell.setFixedHeight(15);
					table.addCell(cell);
					
					if(modelList.get(j).getHasAppendix().equals("Y"))
					    cell = new PdfPCell(new Paragraph("有" ,new Font(bfChinese,10,Font.NORMAL)));
					else
						cell = new PdfPCell(new Paragraph("无" ,new Font(bfChinese,10,Font.NORMAL)));
					cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
					cell.setFixedHeight(15);
					table.addCell(cell);
					
	    		}
	    	}	
			table.setSpacingAfter(10);		
				    
		    float[] widths2 = {12f, 12f, 12f, 12f, 12f, 28f, 12f};
			PdfPTable table2 = new PdfPTable(widths2);
			bfChinese = BaseFont.createFont(font_hei, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); //黑体
			for(int i = 0; i < 7; i++){
				cell = new PdfPCell((new Paragraph(header2[i],new Font(bfChinese,11,Font.NORMAL))));
				cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(21);
				cell.setBorderWidthTop(2);
				cell.setBorderWidthBottom(0);
				table2.addCell(cell);
				
			}
			
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//宋体
			
			cell = new PdfPCell(new Paragraph(devList.get(k).getIsElectric() ,new Font(bfChinese,10,Font.NORMAL)));
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			cell.setFixedHeight(15);
			table2.addCell(cell);		
					
			cell = new PdfPCell(new Paragraph(devList.get(k).getStatus() ,new Font(bfChinese,10,Font.NORMAL)));
		    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		    cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		    cell.setFixedHeight(15);    
		    table2.addCell(cell);		
			
		    int life = 0;
		    for(int j=0; j<modelList.size(); j++){
	    		if(devList.get(k).getModel_sn()==modelList.get(j).getSn()){
	    			life = modelList.get(j).getLife();
	    			break;
	    		}
	    	}
		    
		    cell = new PdfPCell(new Paragraph(String.valueOf(life) ,new Font(bfChinese,10,Font.NORMAL)));
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			cell.setFixedHeight(15);				
			table2.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(String.valueOf(devList.get(k).getElapsed_year()) ,new Font(bfChinese,10,Font.NORMAL)));
		    cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		    cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		    cell.setFixedHeight(15);			    
		    table2.addCell(cell);

			cell = new PdfPCell(new Paragraph(df.format(life - devList.get(k).getElapsed_year()) ,new Font(bfChinese,10,Font.NORMAL)));
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			cell.setFixedHeight(15);				
			table2.addCell(cell);
			
			for(int i=0; i<locList.size(); i++){
				if(locList.get(i).getSn()==devList.get(k).getPosition()){
					cell = new PdfPCell(new Paragraph(locList.get(i).getAddress() ,new Font(bfChinese,10,Font.NORMAL)));
					cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
					cell.setFixedHeight(15);
					
					table2.addCell(cell);
					isNull = false;
				}
			}
			
			if(isNull){
		    	cell = new PdfPCell(new Paragraph("" ,new Font(bfChinese,10,Font.NORMAL)));
				cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
				cell.setFixedHeight(15);
				
				table2.addCell(cell);
		    }	
					
			String keeper_name = "";
			if(keeper_list == null)
			    keeper_name = keeper;
			else{
				for(int i=0; i<keeper_list.size(); i++){
					if(keeper_list.get(i).getSn().equals(devList.get(k).getKeeper())){
						keeper_name = keeper_list.get(i).getName();
						break;
					}
				}
			}
			cell = new PdfPCell(new Paragraph(keeper_name ,new Font(bfChinese,10,Font.NORMAL)));
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
			cell.setFixedHeight(15);
			table2.addCell(cell);		
	
			
			cell = new PdfPCell((new Paragraph("说明：1 .   G---良好，F---故障，S---报废，Y---长期带电，N---非长期带电；", new Font(bfChinese,10,Font.BOLD))));
			cell.setColspan(7);
			cell.setHorizontalAlignment(Cell.LEFT);
			cell.setVerticalAlignment(Cell.ALIGN_BOTTOM);
			cell.setFixedHeight(40);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
		    cell.setBorderWidthRight(0);
			table2.addCell(cell);
			
			cell = new PdfPCell((new Paragraph("              2 .   所有数字以(年)为单位。", new Font(bfChinese,10,Font.BOLD))));
			cell.setColspan(7);
			cell.setHorizontalAlignment(Cell.LEFT);
			cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
			cell.setFixedHeight(15);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
		    cell.setBorderWidthRight(0);
			table2.addCell(cell);
			
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, buffer);
			writer.setBoxSize("signature", new Rectangle(0, 0, 580, 0));//(left,bottom,right,top)
			Footer event = new Footer();
            writer.setPageEvent(event);
            
			document.open();				
			document.add(par);
			document.add(table);
			document.add(table2);
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
