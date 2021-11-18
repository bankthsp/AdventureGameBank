
package Main;

import object.OBJDoor;
import object.ObjBoots;
import object.ObjChest;
import object.ObjKey;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        
        this.gp = gp;
    }
    
    public void setObject(){
        
        gp.obj[0] = new ObjKey();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        
        gp.obj[1] = new ObjKey();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY =40 * gp.tileSize;
        
        gp.obj[2] = new ObjKey();
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;
        
        gp.obj[3] = new OBJDoor();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;
        
        gp.obj[4] = new OBJDoor();
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;
        
        gp.obj[5] = new OBJDoor();
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;
        
        gp.obj[6] = new ObjChest();
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;
        
        gp.obj[7] = new ObjBoots();
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;
    } 
    
}
