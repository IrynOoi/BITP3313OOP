package view;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ViewBookingController;
import model.Booking;
import model.Customer;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;

public class ViewBookingDashboard extends JFrame 
{
    private JPanel contentPane;
    private JTextField BookingIDTxt, BookingDateTxt, CustomerNameTxt, DestinationTxt, DepartureTxt;
    private JTextField ArrivalTxt, AirlineTxt, SeatNoTxt, SeatClassTxt;
    private JTextField PriceTxt, AddOnBaggageTxt, TotalPriceTxt, DefaultBaggageTxt;
    private JTextField DepartureAirportTxt, ArrivalAirportTxt;

    public ViewBookingDashboard(int bookingID, Customer customer) throws ClassNotFoundException, SQLException
    {
        setTitle("View Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 853, 609);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(130, 182, 234));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblViewBooking = new JLabel("BOOKING");
        lblViewBooking.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
        lblViewBooking.setBounds(350, 10, 252, 54);
        contentPane.add(lblViewBooking);

        addLabelAndTextField("Booking ID:", 60, 54, BookingIDTxt = new JTextField());
        addLabelAndTextField("Booking Date:", 420, 54, BookingDateTxt = new JTextField());
        addLabelAndTextField("Name:", 60, 100, CustomerNameTxt = new JTextField());
        addLabelAndTextField("Destination:", 60, 192, DestinationTxt = new JTextField());
        addLabelAndTextField("Departure Airport:", 420, 192, DepartureAirportTxt = new JTextField());
        addLabelAndTextField("Departure Time:", 60, 238, DepartureTxt = new JTextField());
        addLabelAndTextField("Arrival Airport:", 420, 238, ArrivalAirportTxt = new JTextField());
        addLabelAndTextField("Arrival Time:", 60, 284, ArrivalTxt = new JTextField());
        addLabelAndTextField("Airlines:", 420, 284, AirlineTxt = new JTextField());
        addLabelAndTextField("Seat Number:", 60, 330, SeatNoTxt = new JTextField());
        addLabelAndTextField("Class:", 420, 330, SeatClassTxt = new JTextField());
        addLabelAndTextField("Base Price:", 60, 422, PriceTxt = new JTextField());
        addLabelAndTextField("Default Baggage:", 420, 422, DefaultBaggageTxt = new JTextField());
        addLabelAndTextField("Add On Baggage:", 60, 468, AddOnBaggageTxt = new JTextField());
        addLabelAndTextField("Total Price:", 420, 468, TotalPriceTxt = new JTextField());

        JLabel lblSummary = new JLabel("Flight Summary : ");
		lblSummary.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblSummary.setBounds(60, 146, 150, 46);
		contentPane.add(lblSummary);
	
		JLabel lblPayment = new JLabel("Payment Summary : ");
		lblPayment.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblPayment.setBounds(60, 376, 200, 46);
		contentPane.add(lblPayment);

        
        JButton btnBack = new JButton("BACK");
        btnBack.setBounds(21, 520, 80, 40);
        btnBack.addActionListener(e -> {
            CustomerMainMenu frame = new CustomerMainMenu(customer);
            frame.setVisible(true);
            dispose();
            
        });
        contentPane.add(btnBack);

        JButton btnPDF = new JButton("SAVE PDF");
        btnPDF.setBounds(577, 520, 100, 40);
        contentPane.add(btnPDF);
        
        JButton btnDelete_1 = new JButton("DELETE");
        btnDelete_1.setBounds(715, 520, 89, 40);
        btnDelete_1.addActionListener(e -> {
            int confirmDelete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this booking? \nYou can retrieve this booking record in 3 days.\nBooking Record will deleted permanently after 3 days.", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                try {
                    ViewBookingController controller = new ViewBookingController();
                    controller.softDeleteBooking(bookingID);  // Perform the soft delete
                    JOptionPane.showMessageDialog(this, "Booking Record deleted.\nIf you want to retrive this booking record, go to Main Menu-Restore Booking.\nBooking ID: "+bookingID, "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();  // Close the current view
                    CustomerMainMenu frame = new CustomerMainMenu(customer);
                    frame.setVisible(true);
                   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting booking.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(btnDelete_1);
        
        btnPDF.addActionListener(e -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save PDF");
                fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents (*.pdf)", "pdf"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // User selected a file
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();

                    // Ensure file has .pdf extension
                    if (!filePath.toLowerCase().endsWith(".pdf")) {
                        filePath += ".pdf";
                    }

                    // Generate the PDF
                    generatePDF(filePath);  // Only pass the filePath to generatePDF
                    
                    // Show success message
                    JOptionPane.showMessageDialog(this, "PDF saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("PDF generation canceled by user.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error generating PDF!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        
        fetchAndDisplayBookingDetails(bookingID);

    }
    private void addLabelAndTextField(String labelText, int x, int y, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tw Cen MT Condensed Bold", Font.PLAIN, 20));
        label.setBounds(x, y, 200, 46);
        contentPane.add(label);

        textField.setBounds(x + 150, y + 10, 200, 25);
        textField.setEditable(false);
        contentPane.add(textField);
    }

    private void fetchAndDisplayBookingDetails(int bookingID) {
        try {
            ViewBookingController controller = new ViewBookingController();
            Booking booking = controller.getBookingDetails(bookingID);

            if (booking != null) {
                BookingIDTxt.setText(String.valueOf(booking.getBookingId()));
                BookingDateTxt.setText(booking.getBooking_Date());
                CustomerNameTxt.setText(booking.getCustomer().getCustomer_name());
                DestinationTxt.setText(booking.getFlight().getDestination());
                DepartureTxt.setText(booking.getFlight().getDepartureTime());
                ArrivalTxt.setText(booking.getFlight().getArrivalTime());
                AirlineTxt.setText(booking.getAirline().getName());
                SeatNoTxt.setText(booking.getSeat().getSeat_No());
                SeatClassTxt.setText(booking.getSeat().getSeat_Class());
                PriceTxt.setText(String.format("RM%.2f", booking.getSeat().getPrice()));
                AddOnBaggageTxt.setText(String.valueOf(booking.getBaggage().getAddOnWeight())+" kg");
                TotalPriceTxt.setText(String.format("RM%.2f", booking.getTotalPrice()));
                DepartureAirportTxt.setText(booking.getFlight().getDepartureAirport());
                ArrivalAirportTxt.setText(booking.getFlight().getArrivalAirport());
                DefaultBaggageTxt.setText(String.valueOf(booking.getFlight().getDefaultBaggage())+" kg");
            } else {
                JOptionPane.showMessageDialog(this, "No booking details found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching booking details.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void generatePDF(String filePath) throws Exception {
        PdfWriter writer = new PdfWriter(new File(filePath));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Set font and style
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont regularFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // Add Title
        document.add(new Paragraph("FLIGHT TICKET")
            .setFont(boldFont)
            .setFontSize(22)
            .setTextAlignment(TextAlignment.CENTER)
            .setMarginBottom(20));

        // Header Section
        document.add(new Paragraph("Booking ID: " + BookingIDTxt.getText())
            .setFont(boldFont)
            .setFontSize(12)
            .setMarginBottom(10));
        document.add(new Paragraph("Booking Date: " + BookingDateTxt.getText())
            .setFont(regularFont)
            .setFontSize(12)
            .setMarginBottom(10));
        document.add(new Paragraph("Customer Name: " + CustomerNameTxt.getText())
                .setFont(regularFont)
                .setFontSize(12)
                .setMarginBottom(10));

        // Flight Details Table
        float[] columnWidths = {200f, 300f};
        Table table = new Table(columnWidths).useAllAvailableWidth();
        table.addCell(createCell("Flight Information", boldFont, 2));
        table.addCell(createCell("Airline:", regularFont));
        table.addCell(createCell(AirlineTxt.getText(), regularFont));
        table.addCell(createCell("Destination:", regularFont));
        table.addCell(createCell(DestinationTxt.getText(), regularFont));
        table.addCell(createCell("Departure Airport:", regularFont));
        table.addCell(createCell(DepartureAirportTxt.getText(), regularFont));
        table.addCell(createCell("Departure Time:", regularFont));
        table.addCell(createCell(DepartureTxt.getText(), regularFont));
        table.addCell(createCell("Arrival Airport:", regularFont));
        table.addCell(createCell(ArrivalAirportTxt.getText(), regularFont));
        table.addCell(createCell("Arrival Time:", regularFont));
        table.addCell(createCell(ArrivalTxt.getText(), regularFont));
        table.addCell(createCell("Default Baggage:", regularFont));
        table.addCell(createCell(DefaultBaggageTxt.getText(), regularFont));

        // Seat Details Section
        table.addCell(createCell("Seat Information", boldFont, 2));
        table.addCell(createCell("Seat Number:", regularFont));
        table.addCell(createCell(SeatNoTxt.getText(), regularFont));
        table.addCell(createCell("Seat Class:", regularFont));
        table.addCell(createCell(SeatClassTxt.getText(), regularFont));

        // Add the flight and seat details table to the document
        document.add(table);

        // Payment Summary Section
        document.add(new Paragraph("\nPayment Summary")
            .setFont(boldFont)
            .setFontSize(18)
            .setTextAlignment(TextAlignment.CENTER)
            .setMarginTop(20)
            .setMarginBottom(10));

        Table paymentTable = new Table(columnWidths).useAllAvailableWidth();
        paymentTable.addCell(createCell("Base Price:", regularFont));
        paymentTable.addCell(createCell(PriceTxt.getText(), regularFont));
        paymentTable.addCell(createCell("Add-On Baggage:", regularFont));
        paymentTable.addCell(createCell(AddOnBaggageTxt.getText(), regularFont));
        paymentTable.addCell(createCell("Total Price:", regularFont));
        paymentTable.addCell(createCell(TotalPriceTxt.getText(), regularFont));

        // Add the payment details table to the document
        document.add(paymentTable);

        // Footer Section (flight ticket footer style)
        document.add(new Paragraph("\n")
            .setFont(regularFont)
            .setFontSize(10)
            .setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("Thank you for flying with us!")
            .setFont(regularFont)
            .setFontSize(10)
            .setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("Please keep this ticket for your reference.")
            .setFont(regularFont)
            .setFontSize(10)
            .setTextAlignment(TextAlignment.CENTER));

        // Close Document
        document.close();

        System.out.println("PDF saved at: " + filePath);
    }


    private Cell createCell(String text, PdfFont font) {
        return new Cell().add(new Paragraph(text).setFont(font).setFontSize(12).setPadding(5));
    }

    private Cell createCell(String text, PdfFont font, int colspan) {
        return new Cell(1, colspan).add(new Paragraph(text).setFont(font).setFontSize(12).setPadding(5)).setTextAlignment(TextAlignment.CENTER);
    }
    
   
        
    
}
