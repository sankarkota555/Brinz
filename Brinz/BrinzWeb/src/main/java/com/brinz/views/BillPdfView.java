
package com.brinz.views;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brinz.domain.Bill;
import com.brinz.domain.Customer;
import com.brinz.domain.SoldItem;
import com.brinz.utils.BrinzConstantNames;
import com.brinz.utils.BrinzFonts;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class BillPdfView extends AbstractItextPdfView {

  private static Font boldFont = BrinzFonts.boldFont;
  private static Font textFont = BrinzFonts.textFont;
  private static Font footerFont = BrinzFonts.footerFont;
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
  private static final SimpleDateFormat footerDateFormat = new SimpleDateFormat(
      "dd/MMM/yyyy EEEEEE h:mm a");

  private Date billDate = null;
  private String customerName = null;
  private String customerPhone = null;

  private static final Logger log = LoggerFactory.getLogger(BillPdfView.class);

  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    Bill bill = (Bill) model.get("bill");
    billDate = bill.getBillDate();
    doc.addAuthor(BrinzConstantNames.shopName);
    doc.addCreationDate();
    doc.addProducer();

    // set downloadable file name
    response.setHeader("Content-Disposition", "filename="
        + getDownloadableFileName(bill.getBillId(), bill.getCustomer().getCustomerName()));

    // Add header table (office details)
    doc.add(getHeaderTable());

    // Add line separator
    doc.add(getLineSeparator(-10));

    // Add customer details
    //doc.add(getCustomerTable(25, bill.getCustomer(), dateFormat.format(billDate)));

    // Add sold items
    doc.add(getPurchaseDetailsTable(25, bill.getSoldItems(), bill.getTotalAmount(), bill.getCustomer().getCreditAmoount()));

    // Add line separator
    doc.add(getLineSeparator(-26));

    // Add Footer table
    doc.add(getFooter(15));

    // Add notes to customer
    doc.add(getNotes(0));

   /* Image barCodeImage = getBarcodeImage(bill.getBillId(), bill.getTotalAmount());
    barCodeImage.setAbsolutePosition(450, 625);
    // Add QR code image
    doc.add(barCodeImage);*/

    doc.addSubject("Bill receipt");

  }

  private Image getBarcodeImage(long billId, long totalAmount) {
    StringBuilder stringBuilder = new StringBuilder(BrinzConstantNames.shopName);
    stringBuilder.append("\n Customer Name:");
    stringBuilder.append(customerName);
    stringBuilder.append("\n Customer Phone:");
    stringBuilder.append(customerPhone);
    stringBuilder.append("\n Bill ID:");
    stringBuilder.append(billId);
    stringBuilder.append("\n Total Amount:");
    stringBuilder.append(totalAmount);
    stringBuilder.append("\n Date:");
    stringBuilder.append(billDate);
    BarcodeQRCode barcodeQRCode = new BarcodeQRCode(stringBuilder.toString(), 120, 120, null);
    try {
      return barcodeQRCode.getImage();
    } catch (BadElementException badElementException) {
      log.error("exception while creating QR code image,{}", badElementException);
    }
    return null;
  }

  private PdfPTable getHeaderTable() throws DocumentException {

    final int phoneHeight = 25;

    //try {

     /* String folderPath = getFolderLocation();
      // image path:
      String imageFilePathRight = folderPath + "resources/images/lakshmi.jpg";
      // Pictures/Tulips.jpg";
      String imageFilePathLeft = folderPath + "resources/images/ganapathi.jpg";
      Image imageLeft = Image.getInstance(imageFilePathLeft);
      imageLeft.setWidthPercentage(20);
      imageLeft.setScaleToFitHeight(true);

      Image imageRight = Image.getInstance(imageFilePathRight);
      imageRight.setWidthPercentage(20);
      imageRight.setScaleToFitHeight(true);*/

      float[] tableWidths = new float[] { 15f, 55f, 15f };
      PdfPTable headerTable = new PdfPTable(3);
      headerTable.setWidths(tableWidths);
      headerTable.setWidthPercentage(100);



     /* PdfPTable innerTable = new PdfPTable(1);
      innerTable.setWidthPercentage(100);  */
      Paragraph heading = new Paragraph(BrinzConstantNames.shopName, BrinzFonts.headingFont);

      PdfPCell headingCell = new PdfPCell();
      headingCell.setColspan(2);
      headingCell.setPhrase(heading);
      headingCell.setBorder(Rectangle.NO_BORDER);
      headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      headingCell.setColspan(3);
      
      
    // add company heading cell
      headerTable.addCell(headingCell);
    
      PdfPCell phoneLeftCell = new PdfPCell(new Phrase("Phone num1"));
      phoneLeftCell.setBorder(Rectangle.NO_BORDER);
      phoneLeftCell.setFixedHeight(phoneHeight);
      phoneLeftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      phoneLeftCell.setColspan(2);
      
      headerTable.addCell(phoneLeftCell);
      
      PdfPCell phoneRightCell = new PdfPCell(new Phrase("Phone num2"));
      phoneRightCell.setBorder(Rectangle.NO_BORDER);
      phoneRightCell.setFixedHeight(phoneHeight);
      phoneRightCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      
      headerTable.addCell(phoneRightCell);
      
      PdfPCell customerNameCell = new PdfPCell(new Phrase("Cus Name"));
      customerNameCell.setBorder(Rectangle.NO_BORDER);
      customerNameCell.setFixedHeight(phoneHeight);
      customerNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      customerNameCell.setColspan(3);
      
      headerTable.addCell(customerNameCell);

      // add name in center
     // innerTable.addCell(textCell);

      /*PdfPCell addressCell = new PdfPCell();
      addressCell.setPaddingTop(10f);
      addressCell.setBorder(Rectangle.NO_BORDER);
      addressCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      addressCell.setPhrase(new Phrase(BrinzConstantNames.shopAddress, footerFont));
      innerTable.addCell(addressCell);
     */
    /*  PdfPCell imageCellRight = new PdfPCell(imageRight, true);
      imageCellRight.setBorder(Rectangle.NO_BORDER);
      imageCellRight.setFixedHeight(imgeHeight);
      imageCellRight.setHorizontalAlignment(Element.ALIGN_RIGHT);*/

      // add left side image
      //table.addCell(pdfCell);
      /*PdfPCell tebleCell = new PdfPCell(innerTable);
      tebleCell.setBorder(Rectangle.NO_BORDER);

      table.addCell(tebleCell);
*/
      // add right side image
      //table.addCell(imageCellRight);

      return headerTable;
      
    /*} catch (BadElementException badElementException) {
      log.error("BadElementException while creating header table, {}", badElementException);
    } catch (MalformedURLException malformedURLException) {
      log.error("MalformedURLException while creating header table, {}", malformedURLException);
    } catch (IOException ioException) {
      log.error("IOException while creating header table, {}", ioException);
    }*/

    //return null;

  }

  private static Chunk getLineSeparator(int offSet) {
    DottedLineSeparator lineSeparator = new DottedLineSeparator();
    lineSeparator.setAlignment(Element.ALIGN_BOTTOM);
    lineSeparator.setOffset(offSet);
    lineSeparator.setLineWidth(2f);
    return new Chunk(lineSeparator);
  }

  private PdfPTable getCustomerTable(int spaceTop, Customer customer, String generatedDate)
      throws DocumentException {

    customerName = customer.getCustomerName();
    customerPhone = customer.getPhone();

    PdfPTable customerTable = new PdfPTable(2);

    customerTable.setSpacingBefore(spaceTop);

    float[] columnWidths = new float[] { 6f, 20f };
    customerTable.setWidths(columnWidths);
    // customerTable.setWidthPercentage(80);
    customerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

    // customer name
    customerTable.addCell(new Phrase("Name :", boldFont));
    customerTable.addCell(new Phrase(customerName, textFont));

    // customer Phone
    customerTable.addCell(new Phrase("Phone :", boldFont));
    customerTable.addCell(new Phrase(customerPhone, textFont));

    // customer Address
    customerTable.addCell(new Phrase("Address :", boldFont));
    customerTable.addCell(new Phrase("No address column - remove it", textFont));

    // bill date
    customerTable.addCell(new Phrase("Date :", boldFont));
    customerTable.addCell(new Phrase(generatedDate, textFont));

    return customerTable;
  }

  private PdfPTable getPurchaseDetailsTable(int spaceTop, List<SoldItem> soldItems, long netAmount, long creditAmount)
      throws DocumentException {
    float amount;

    PdfPTable detailsTable = new PdfPTable(5);

    detailsTable.setSpacingBefore(spaceTop);

    float[] columnWidths = new float[] { 20f, 18f, 12f, 20f, 20f };
    detailsTable.setWidths(columnWidths);
    detailsTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    detailsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

    detailsTable.addCell(new Phrase("Item Name", boldFont));
    detailsTable.addCell(new Phrase("No.of Bags", boldFont));
    detailsTable.addCell(new Phrase("Price", boldFont));
    detailsTable.addCell(new Phrase("No.of KGS", boldFont));
    detailsTable.addCell(new Phrase("Amount", boldFont));


    for (SoldItem soldItem :soldItems) {
      // item name
      detailsTable.addCell(new Phrase(soldItem.getItem().getItemName(), textFont)); // item name
      detailsTable.addCell(
          new Phrase(String.valueOf(soldItem.getNoOfBags()), textFont)); // No.of bags
      detailsTable.addCell(new Phrase(String.valueOf(soldItem.getSoldPrice()), textFont)); // price
      detailsTable.addCell(new Phrase(String.valueOf(soldItem.getSoldQuantity()), textFont)); // No.of KGS
      amount = (soldItem.getSoldQuantity() * soldItem.getSoldPrice());
      detailsTable.addCell(new Phrase(String.valueOf(amount), textFont)); // amount

    }
    
    PdfPCell spaceCell = getPurchaseDetailsCell(null, 4);
    /*spaceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    spaceCell.setBorder(Rectangle.NO_BORDER);
    spaceCell.setColspan(4);*/
    //spaceCell.setPhrase(new Phrase(String.valueOf(netAmount), boldFont));
    
    // add space cell 
    detailsTable.addCell(spaceCell);
    
    PdfPCell bottomLineCell = new PdfPCell();
    //bottomLineCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    bottomLineCell.setPaddingTop(5);
    bottomLineCell.setMinimumHeight(10);
    bottomLineCell.setBorder(Rectangle.BOTTOM);
    //bottomLineCell.setColspan(4);
    
    // add bottom line cell 
    detailsTable.addCell(bottomLineCell);

    PdfPCell totalAmount = new PdfPCell();
    totalAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
    totalAmount.setBorder(Rectangle.NO_BORDER);
    //totalAmount.setColspan(5);
    totalAmount.setPhrase(new Phrase(String.valueOf(netAmount), boldFont));
    //totalAmount.setPhrase((new Phrase("Total Amount", boldFont)));

   // detailsTable.addCell(totalAmount);
   // detailsTable.addCell(new Phrase(String.valueOf(netAmount), boldFont)); // total amount
   
    detailsTable.addCell(spaceCell);  // add space cell
    detailsTable.addCell(totalAmount); // add total amount
    
    
    PdfPCell customerCreditAmount = getPurchaseDetailsCell("Credit/debit", 4);
    /*customerCreditAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
    customerCreditAmount.setBorder(Rectangle.NO_BORDER);
    customerCreditAmount.setColspan(4);
    customerCreditAmount.setPhrase(new Phrase(String.valueOf("Credit/debit"), boldFont));*/
    
   
    detailsTable.addCell(customerCreditAmount);  // add customer credit amount
    detailsTable.addCell(new Phrase(String.valueOf(creditAmount), boldFont));
    
    detailsTable.addCell(spaceCell); // add space cell
    detailsTable.addCell(bottomLineCell); // add bottom line
    
    detailsTable.addCell(getPurchaseDetailsCell("Final Amount", 4)); // add final amount
    detailsTable.addCell(new Phrase(String.valueOf(netAmount+ creditAmount), boldFont)); // add final amount
    

    return detailsTable;
    
  }
  
  private PdfPCell getPurchaseDetailsCell(String phrase, Integer colSpan){
    PdfPCell pdfCell = new PdfPCell();
    pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    pdfCell.setBorder(Rectangle.NO_BORDER);
    if(colSpan != null)
      pdfCell.setColspan(colSpan);
    if(phrase != null)
      pdfCell.setPhrase(new Phrase(phrase, boldFont));
    
    return pdfCell;
  }

  private PdfPTable getFooter(int spaceBefore) {

    PdfPTable footerTable = new PdfPTable(2);

    footerTable.setSpacingBefore(spaceBefore);
    footerTable.setWidthPercentage(100);

    PdfPCell cell = new PdfPCell(new Phrase(footerDateFormat.format(billDate), footerFont));
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(Rectangle.NO_BORDER);
    footerTable.addCell(cell);

    PdfPCell cellTwo = new PdfPCell(new Phrase("Thank You", footerFont));
    cellTwo.setBorder(Rectangle.NO_BORDER);
    cellTwo.setHorizontalAlignment(Element.ALIGN_RIGHT);
    footerTable.addCell(cellTwo);

    return footerTable;

  }

  private static Paragraph getNotes(float spacinngBefore) {
    Paragraph paragraph = new Paragraph();
    paragraph.setAlignment(Rectangle.ALIGN_CENTER);
    paragraph.setFont(footerFont);
    paragraph.add(BrinzConstantNames.note);
    paragraph.setSpacingBefore(spacinngBefore);
    return paragraph;
  }

  private String getFolderLocation() throws UnsupportedEncodingException {
    return URLDecoder.decode(
        this.getClass().getClassLoader().getResource("").getPath().split("WEB-INF/classes/")[0],
        "UTF-8");
  }

  private String getDownloadableFileName(long billId, String customerName) {
    return "LGC_bill_" + customerName + "_" + billId + ".pdf";
  }
}
