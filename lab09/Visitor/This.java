

public class This implements Element {
   // 1. accept(Visitor) implementation
   public void   accept( Visitor v ) {
     v.visit( this );
   } 
   
   public String thiss() {
     return "This";
   }
}
