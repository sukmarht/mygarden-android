package com.example.kebunku.Adapter;

import android.text.PrecomputedText;
import android.util.Log;

public class DataPosisi {
    public int x = 0; // cm
    public int y = 0;
    public int z = 0;

    public int maxPoint = 25; // n*n
    public int point = 0; // point now
    public int nRow = 5; // row
    public int nColumn = 5; // column
    public int xDim = 120; // real dimension cm
    public int yDim = 120; // real dimension cm

    public int update(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;

        int pointx = x / (this.xDim / this.nColumn) + 1;
        int pointy = y / (this.yDim / this.nRow) + 1;

        if(pointy % 2 == 0){
            this.point = pointy * this.nColumn - (pointx - 1);
        } else {
            this.point = pointy * this.nColumn - (this.nColumn - pointx);
        }
        Log.d("DEBUGSUKMA", "Point now: "  + this.point);
        return this.point;
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
//        this.x = x;
//        this.y = y;
//        this.z = z;
        return "X"+x+" Y"+y+" Z"+z;
    }
    public String goByGrid(int x, int y, int columnScale, int rowScale){
//        this.x = posx;
//        this.y = posy;
        int[] pos = getCmByPos(x, y, columnScale, rowScale);
        return "X"+ pos[0] + " Y" + pos[1];
    }
    // pos adalah posisi referensi berdasarkan kotak kotak
    public int[] getCmByPos(int x, int y, int columnScale, int rowScale){
        int posx = (int) (Math.round(((float) this.xDim /columnScale)*.5) + Math.round(x*((float) this.xDim /columnScale)));
        int posy = (int) (Math.round(((float) this.yDim /rowScale)*.5) + Math.round(y*((float) this.yDim /rowScale)));
        int[] result = {posx, posy};
        return result;
    }
    public String String(){
        return "X_POS_CM: " + this.x + " Y_POS_CM: " + this.y+ " Z_POS_CM: " + this.z;
    }

    public int[] getPosByPoint(int x){
        x = x - 1;
        int pointy = x / this.nColumn;
        int pointx = 0;

        if(pointy % 2 == 1){
            pointx = this.nColumn * (pointy+1) - x - 1;
        } else {
            pointx = x % this.nColumn;
        }
        int[] result = {pointx, pointy};
        return result;
    }
}
