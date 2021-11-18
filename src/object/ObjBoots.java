
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ObjBoots extends SuperObject {
     public ObjBoots() {
        
        name = "Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
