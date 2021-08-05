package com.example.kebunku.Adapter;

public class DataPosisi {
    private int x = 0;
    private int y = 0;
    private int z = 0;
    public int xDim = 120;
    public int yDim = 120;

    public boolean update(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        return true;
   }
    public String Zmax(){
        if(this.z > 0) return "Z"+(--this.z);
        return "Z0";
    }
    public String Zmin(){
        return "Z"+(++this.z);
    }
    public String Ymin(){
        if(this.y > 0) return "Y"+(--this.y);
        return "Y0";
    }
    public String Ymax(){
        return "Y"+(++this.y);
    }
    public String Xmax(){
        return "X"+(++this.x);
    }
    public String Xmin(){
        if(this.x > 0) return "X"+(--this.x);
        return "X0";
    }
    public String go(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        return "X"+x+" Y"+y+" Z"+z;
    }
    public String goByGrid(int x, int y, int columnScale, int rowScale){
        int posx = (int) (Math.round(((float) this.xDim /columnScale)*.5) + Math.round(x*((float) this.xDim /columnScale)));
        int posy = (int) (Math.round(((float) this.yDim /rowScale)*.5) + Math.round(y*((float) this.yDim /rowScale)));
        this.x = posx;
        this.y = posy;
        return "X"+ posx + " Y" + posy;
    }
    public String goByPoint(int x){
        int posxP1 = 12;
        int posyP1 = 12;
        this.x = posxP1;
        this.y = posyP1;
        return "X"+ posxP1 + " Y" + posyP1;
    }
    public String String(){
        return "X_POS_CM: " + this.x + " Y_POS_CM: " + this.y+ " Z_POS_CM: " + this.z;
    }
}
