
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
class Tas {
  private int x ;
  private int y ;
  public final int en = 45;
  public final int boy = 45;
  private boolean ustubos=true;

    public boolean isUstubos() {
        return ustubos;
    }

    public void setUstubos(boolean ustubos) {
        this.ustubos = ustubos;
    }
  
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
  
}
class Asiltas{
     private int x ;
  private int y ;
  public final int en = 25;
  public final int boy = 25;
  private boolean secildi = true;

    public boolean isSecildi() {
        return secildi;
    }

    public void setSecildi(boolean secildi) {
        this.secildi = secildi;
    }
 
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Asiltas(int x, int y) {
        this.x = x;
        this.y = y;
    }

}


public class Oyun extends JPanel implements MouseListener,ActionListener{

      final int tabloboy = 500;
      final int tabloen = 500;
      final int tablox = 400;
      final int tabloy = 100;
      boolean basili = false;
      Timer timer ;
    ArrayList<Tas> taslar = new ArrayList<>();//alt taraf siyah olan kısım
    Asiltas [] asilTaslar = new Asiltas[33];
     int secilindex=-1 ;
     Asiltas alinan ;
     boolean releasedengeliyom = false;
    public Oyun() {
        Thread tr = new Thread(new Runnable() {

            @Override
            public void run() {
                 int y = 126;
        int x = 497;
        for(int i = 0;i<7;i++){
            taslar.add(new Tas(562,y));
            if(i == 2 || i==3 || i == 4){
                for(int k = 0;k<2;k++){
                taslar.add(new Tas(x,y));
                x-=65;
                }
                x=497;  
            }
            y+=65;
        }
        y=126;
        for(int i = 0;i<7;i++){
            taslar.add(new Tas(627,y));
            y+=65;
        }
        y=126;
        x=692+65;
        for(int i = 0;i<7;i++){
            taslar.add(new Tas(692,y));
              if(i == 2 || i==3 || i == 4){
                for(int k = 0;k<2;k++){
                taslar.add(new Tas(x,y));
                x+=65;
                }
                x=692+65;  
            }
        y+=65;
        }
           
        int i=0;
        for(int z = 0 ; z <33 ; z++){
            asilTaslar[i]=new Asiltas(taslar.get(z).getX()+10,taslar.get(z).getY()+10);
            if(z==16) asilTaslar[z].setSecildi(false);
            i++;
        }
        for(int z = 0 ;z<33;z++){
            Rectangle rect = new Rectangle(taslar.get(z).getX(),taslar.get(z).getY(),45,45);
            for(int a = 0 ;a<33;a++){
                if(rect.contains(asilTaslar[a].getX(),asilTaslar[a].getY(),25,25) && asilTaslar[a].isSecildi()==true){
                    taslar.get(z).setUstubos(false);
                    break;
                }
            }
        }
        
            }
        });
        tr.start();
          try {
              tr.join();
          } catch (InterruptedException ex) {
              Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
          }
          timer = new Timer(10,this);
          timer.start();
        System.out.println(taslar.size());
        System.out.println(asilTaslar.length);
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.red);
        g.fillOval(tablox,tabloy,tabloen,tabloboy);
        g.setColor(Color.BLACK);
        g.drawLine(530,130,530,227);
        g.drawLine(431,227,530,227);
        g.drawLine(763,130,763,227);
        g.drawLine(763,227,864,227);
        g.drawLine(530,464,530,571);
        g.drawLine(530,464,428,464);
        g.drawLine(763,464,763,574);
        g.drawLine(763,464,872,464);
        g.setColor(Color.BLACK);
        for(Tas tas : taslar ){
            g.fillOval(tas.getX(),tas.getY(),tas.en,tas.boy);
        }
        g.setColor(Color.white);
        for(Asiltas asil : asilTaslar){
            if(asil.isSecildi())
            g.fillOval(asil.getX(),asil.getY(),asil.en,asil.boy);
        }

         if(releasedengeliyom){
             releasedengeliyom=false;
        for(int z = 0 ;z<33;z++){
            if(boskontrol(new Rectangle(taslar.get(z).getX(),taslar.get(z).getY(),45,45))){
                taslar.get(z).setUstubos(true);
            }
            else {
                taslar.get(z).setUstubos(false);
            }
        }
            if(hamleKontrol()==false){
                    
                     for( int i=0;i<33;i++){
                         if(taslar.get(i).isUstubos()==false)System.out.println(i);
                     }
                     JOptionPane.showMessageDialog(this,"KALAN PIYON = "+kacPiyon());
                     timer.stop();
                     System.exit(0);
                 
        }
         
         }
           
        
        
    }
  public boolean boskontrol(Rectangle rect){
      
      for(int i = 0;i<33;i++){
          if(rect.contains(asilTaslar[i].getX(),asilTaslar[i].getY(),25,25) && asilTaslar[i].isSecildi()==true){
              return false;
          }
      }
      return true;
  }
    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
        System.out.println(e.getX());
        System.out.println(e.getY());
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
         System.out.println("ss"+e.getY());
         System.out.println("ss"+e.getY());
        Rectangle rect = new Rectangle(e.getX(),e.getY(),5,5);
        
          for(int i = 0;i<33;i++){
              if(asilTaslar[i].isSecildi()==false) continue;
              if(rect.intersects(new Rectangle(asilTaslar[i].getX(),asilTaslar[i].getY(),25,25))){
                  secilindex = i;
                  basili =  true;
                  alinan = new Asiltas(asilTaslar[i].getX(),asilTaslar[i].getY());
                  return ;
              }
          }
      
         
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //uzaklık kontrolu yap direkte bosa gecebılır +++ 
        basili = false;//bıraktıgımda basmayı hala daha cızdırmemesı ıcın
        if(secilindex == -1 )return;//piyon secilmemisse cokmemesı icin

        Rectangle rect = new Rectangle(asilTaslar[secilindex].getX(),asilTaslar[secilindex].getY(),25,25);
        
        
        for(int i = 0; i< 33 ;i++){//pıyon uzerıne koymama kontrolu 
            if(rect.intersects(new Rectangle(asilTaslar[i].getX(),asilTaslar[i].getY(),25,25)) && i!=secilindex && asilTaslar[i].isSecildi()==true){
                asilTaslar[secilindex].setX(alinan.getX());                                                        //onceden olenler ıle kontrol etmesın
                asilTaslar[secilindex].setY(alinan.getY());
                secilindex = -1;
                return ;
            }
        }
        for(int i = 0;i<33;i++){//siyah tasa koyma kontrolu
            Rectangle rectdik = new Rectangle(taslar.get(i).getX(),taslar.get(i).getY(),45,45);
            if(rectdik.contains(rect)){
            asilTaslar[secilindex].setX(taslar.get(i).getX()+10);
            asilTaslar[secilindex].setY(taslar.get(i).getY()+10);    
             
            if(asilTaslar[secilindex].getX()==alinan.getX() && asilTaslar[secilindex].getY()==alinan.getY()){ 
                secilindex = -1;
                return;//alıp tekrar yerıne bırakmıssam
            }
            if(asilTaslar[secilindex].getX()!=alinan.getX() && asilTaslar[secilindex].getY()!=alinan.getY()){
                 asilTaslar[secilindex].setX(alinan.getX());                              //onceden olenler ıle kontrol etmesın
                asilTaslar[secilindex].setY(alinan.getY());
                secilindex = -1;
                return;
            }//capraz bır degısım yapılmıs
            
  
            if(asilTaslar[secilindex].getX() == alinan.getX()){//y de hareket yapılmıs
                
                if(Math.abs(asilTaslar[secilindex].getY()-alinan.getY()) < 90 || Math.abs(asilTaslar[secilindex].getY()-alinan.getY()) > 160){
                asilTaslar[secilindex].setX(alinan.getX());                                                     
                asilTaslar[secilindex].setY(alinan.getY());
                secilindex = -1;
                return;//cok uzaga gıtmeyı veya ustunden gecmeden piyon tasımayı engeller.
                }
                //sadece oldurdugunde sıyahı true yapıyorsun olmezse false kalıyor 
                for(int k = 0 ;k<33;k++){
                    if(asilTaslar[k].isSecildi()==false)continue;//eskiden gidenler tekrar gitmesin diye
                   if(asilTaslar[secilindex].getY() > alinan.getY()){
                       if((alinan.getY()+65 == asilTaslar[k].getY()) &&(asilTaslar[k].getX()==alinan.getX())){
                    asilTaslar[k].setSecildi(false);
                    secilindex = -1;
                    releasedengeliyom=true;
                    repaint();
                    
         
                    return;
                       }
                   }
                   else {
                    if((alinan.getY()-65 == asilTaslar[k].getY()) && (asilTaslar[k].getX()==alinan.getX())){
                    asilTaslar[k].setSecildi(false);
                    secilindex = -1;
                    releasedengeliyom=true;
                    repaint();
                   
            
                    return;
                       }
                       
                   }
                }
            }else {//x te hareket var 
                
                if(Math.abs(asilTaslar[secilindex].getX()-alinan.getX()) < 90 || Math.abs(asilTaslar[secilindex].getX()-alinan.getX()) > 160){
                asilTaslar[secilindex].setX(alinan.getX());                                                       
                asilTaslar[secilindex].setY(alinan.getY());
                secilindex = -1;
                return;//cok uzaga gıtmeyı veya ustunden gecmeden piyon tasımayı engeller.
                }
                
                
                 for(int z = 0 ;z<33;z++){
                     if(asilTaslar[z].isSecildi()==false)continue;
                   if(asilTaslar[secilindex].getX() > alinan.getX()){
                       if(alinan.getX()+65==asilTaslar[z].getX() && (asilTaslar[z].getY()==alinan.getY())){
                    asilTaslar[z].setSecildi(false);
                    secilindex = -1;
                 releasedengeliyom=true;
                    repaint();
                   
             
                    return;
                       }
                   }
                   else {
                        if(alinan.getX()-65==asilTaslar[z].getX() && (asilTaslar[z].getY()==alinan.getY())){
                    asilTaslar[z].setSecildi(false);
                    secilindex = -1;
                    releasedengeliyom=true;
                    repaint();
                     
           
                    return;
                       }
                   }
                }
            }
     
          }
            else{//siyaha koymama durumu
            //System.out.println(i);
              asilTaslar[secilindex].setX(alinan.getX());
              asilTaslar[secilindex].setY(alinan.getY());
              
            }
        }

    }
    

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    
        public boolean hamleKontrol(){
   
        for(int i = 0;i<33;i++){
            if(asilTaslar[i].isSecildi()==false)continue;
            for(int k = 0;k<33;k++){
               
                if(asilTaslar[i].getY()+65==asilTaslar[k].getY() && asilTaslar[k].isSecildi() && asilTaslar[i].getX()==asilTaslar[k].getX()){//asagı kontrol
                 Rectangle rect = new Rectangle(asilTaslar[k].getX(),asilTaslar[k].getY()+65,25,25);
                    for(int z = 0 ;z<33;z++){
                     if(rect.intersects(new Rectangle(taslar.get(z).getX(),taslar.get(z).getY(),45,45)) && taslar.get(z).isUstubos()){
                          return true;
                      }
                      
                      
                   }
                }
                if(asilTaslar[i].getY()-65==asilTaslar[k].getY() && asilTaslar[k].isSecildi() && asilTaslar[i].getX()==asilTaslar[k].getX()){//yukarı gıdıs
                     Rectangle rect = new Rectangle(asilTaslar[k].getX(),asilTaslar[k].getY()-65,25,25);
                    for(int z = 0 ;z<33;z++){
                        if(rect.intersects(new Rectangle(taslar.get(z).getX(),taslar.get(z).getY(),45,45)) && taslar.get(z).isUstubos()){
                          return true;
                      }
                      
                       
                   }
                }
                if(asilTaslar[i].getX()+65==asilTaslar[k].getX() && asilTaslar[k].isSecildi() && asilTaslar[i].getY()==asilTaslar[k].getY()){// saga
                     Rectangle rect = new Rectangle(asilTaslar[k].getX()+65,asilTaslar[k].getY(),15,15);
                    for(int z = 0 ;z<33;z++){
                      if(rect.intersects(new Rectangle(taslar.get(z).getX(),taslar.get(z).getY(),45,45)) && taslar.get(z).isUstubos()){
                          return true;
                      }
                      
                   }
                }
                
                if(asilTaslar[i].getX()-65==asilTaslar[k].getX() && asilTaslar[k].isSecildi() && asilTaslar[i].getY()==asilTaslar[k].getY()){//sola
                      Rectangle rect = new Rectangle(asilTaslar[k].getX()-65,asilTaslar[k].getY(),15,15);
                    for(int z = 0 ;z<33;z++){
                      if(rect.intersects(new Rectangle(taslar.get(z).getX(),taslar.get(z).getY(),45,45)) && taslar.get(z).isUstubos()){
                          return true;
                      }
                      
                   }
                }
               
               
            }
            
        }
        return false;
 
    }
    public int kacPiyon(){
        int count = 0;
        for(int i = 0;i<33;i++){
            if(asilTaslar[i].isSecildi())count++;
        }
        return count;
    }
    public int ustubos(){
        int count = 0;
        for(int i = 0;i<33;i++){
            if(taslar.get(i).isUstubos())count++;
        }
        return count;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
      if(basili){
          Point p = MouseInfo.getPointerInfo().getLocation();
            
            asilTaslar[secilindex].setX(p.x-15);
            asilTaslar[secilindex].setY(p.y-27);
                  repaint();
      }
      repaint();
        
    }
    
    
    
    
}
