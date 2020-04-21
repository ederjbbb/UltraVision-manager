package testPackage;

enum PaymentType {

  CARD("Card has taxes"),
  CASH("cash is clear"),
  POINTS("points no profit"),
  ONRETURN("not ready to pay now");
 private String desc;

 PaymentType(String s) {
  this.desc = s;
 }

 public String getDesc(){
    return this.desc;
 }
}

class Test{
 PaymentType paymentType;


 public Test(PaymentType paymentType) {
  this.paymentType = paymentType;
  setPaymentType();
 }
 public void setPaymentType(){
   switch (paymentType){
    case CASH:
        System.out.println("Payment in cash");
        break;
    case CARD:
     System.out.println("Payment in card");
     break;
    case POINTS:
     System.out.println("Payment in pints");
     break;
    case ONRETURN:
     System.out.println("to be paid onreturn ");
     break;
    default:
     System.out.println("not paid yet");

   }

 }


 public static void main(String[] args) {

  PaymentType pp;
  pp = PaymentType.valueOf("POINTS");
  System.out.println(pp.getDesc());






 }
}






