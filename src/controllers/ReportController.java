//package controllers;
//
//import classManagers.ItemsManager;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import models.ReportRentalData;
//import view.ViewFactory;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class ReportController extends MainController implements Initializable {
//    public ReportController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
//        super(itemsManager, viewFactory, fxmlName);
//    getDataOnReport();
//
//
//    }
//    public ReportController(){}
//    @FXML
//    public Label reportSurname;
//
//    @FXML
//    private Label reportPickupDate;
//
//    @FXML
//    private Label reportReturnDate;
//
//    @FXML
//    private Label reportRemainingPoints;
//
//    @FXML
//    private Label ReportChargedCard;
//
//    @FXML
//    private Label ReportItems;
//
//    @FXML
//    private Label reportName;
//
//    @FXML
//    private Label reportReceiptNumber;
//    @FXML
//    private Label membershipReport;
//    @FXML
//    private Label reportNºOfItems;
//
//    @FXML
//    void ConfirmTransaction(MouseEvent event) {
//        closeWindow();
//    }
//
//
//    @FXML
//    void backToRentalControl(MouseEvent event) {
//        closeWindow();
//    }
//    private void closeWindow(){
//        Stage stage = (Stage)reportName.getScene().getWindow();
//        viewFactory.closeStage(stage);
//    }
//    ReportRentalData report ;
//
//    public void getDataOnReport(){
//
//
////        reportName.setText(report.getFirstName());
////        reportSurname.setText(report.getLastName());
////        reportPickupDate.setText(rentalViewController.getPickupDate());
////        reportReturnDate.setText(String.valueOf(reportReturnDate));
////        ReportChargedCard.setText(Double.toString(report.getAmountChargedOnCard()));
////        membershipReport.setText(report.getMemberNumber());
////        reportRemainingPoints.setText(Integer.toString(report.getPendings()));
////        reportNºOfItems.setText(Integer.toString(report.getPendings()));
////        reportReceiptNumber.setText(report.getReceiptNumber());
////        System.out.println(report.getFirstName());
//        viewFactory.showReport();
//
//
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//
//    }
//}
