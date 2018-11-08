package cn.com.edu.aib.trafficclient_2017gb.osc.roadview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;
import java.util.Set;

import cn.com.edu.aib.trafficclient_2017gb.R;

/**
 * Created by teacher on 2018/11/8.
 */

public class RoadView extends View{

    Bitmap bmp;
    Bitmap buf;
    Canvas cache;
    HashMap<Integer,RoadData> map;

    String date = "";
    String week = "";
    String shidu = "";
    String wendu = "";
    String pm25 = "";

    public RoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        buf = Bitmap.createBitmap(1280,689,bmp.getConfig());
        cache = new Canvas(buf);
        Rect area = new Rect(0,0,1280,689);
        cache.drawBitmap(bmp,null,area,null);
        map = new HashMap<>();
    }

    public RoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public RoadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoadView(Context context) {
        this(context, null);
    }

    Paint paint = new Paint();
    int[] color = new int[]{Color.parseColor("#6ab82e")
            ,Color.parseColor("#ece93a")
            ,Color.parseColor("#f49b25")
            ,Color.parseColor("#e33532")
            ,Color.parseColor("#b01e23")};

    String[] roadji = new String[]{"畅通" ,"缓行",  "一般拥堵"  ,"中度拥堵", "严重拥堵"};


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(color[0]);
        cache.drawRect(new Rect(60,310,394,373),paint);

        paint.setColor(Color.parseColor("#D5D7DA"));
        cache.drawRect(new Rect(760,40,1240,640),paint);

        Set<Integer> key = map.keySet();

        //道路颜色
        for(Integer k : key){
            RoadData data = map.get(k);
            if(k==5){
                for(int i = 0 ; i < data.endX.length ; i++){
                    paint.setColor(color[data.getLevel()]);
                    Rect rect = new Rect(data.startX[i],data.startY[i],data.endX[i],data.endY[i]);
                    cache.drawRect(rect,paint);
                }
            }else{
                paint.setColor(color[data.getLevel()]);
                Rect rect = new Rect(data.startX[0],data.startY[0],data.endX[0],data.endY[0]);
                cache.drawRect(rect,paint);
            }
        }


        //道路名字
        paint.setColor(Color.WHITE);
        paint.setTextSize(24);
        cache.drawText(map.get(5).getRoadName()+"",300,60,paint);
        cache.drawText(map.get(5).getRoadName()+"",300,642,paint);
        cache.drawText(map.get(7).getRoadName()+"",492,320,paint);

        cache.rotate(90);

        cache.drawText(map.get(1).getRoadName()+"",140,-190,paint);
        cache.drawText(map.get(2).getRoadName()+"",140,-350,paint);
        cache.drawText(map.get(3).getRoadName()+"",440,-350,paint);
        cache.drawText(map.get(4).getRoadName()+"",440,-190,paint);
        cache.drawText(map.get(5).getRoadName()+"",300,-35,paint);
        cache.drawText(map.get(6).getRoadName()+"",300,-666,paint);

        cache.rotate(-90);

        //绘制天气信息
        paint.setColor(Color.BLACK);
        paint.setTextSize(42);
        cache.drawText(date,760,120,paint);
        cache.drawText(week,800,180,paint);

        paint.setTextSize(28);
        cache.drawText(wendu,1000,120,paint);
        cache.drawText(shidu,1000,170,paint);
        cache.drawText(pm25,1000,220,paint);


        cache.drawText("道路拥挤程度示意:",760,360,paint);
                paint.setTextSize(18);
                for(int i = 0 ; i < 5 ; i++){
                    paint.setColor(Color.BLACK);
                    cache.drawText(roadji[i],760,390+30*i+14,paint);
                    paint.setColor(color[i]);
                    cache.drawRect(new Rect(840,390+30*i,900,410+30*i),paint);
                }


        Rect dst = new Rect(1000,340,1240,590);
        Bitmap bit = BitmapFactory.decodeResource(getResources(),R.drawable.policemen);
        cache.drawBitmap(bit,null,dst,null);


        //网格
        for(int pos =0 ;pos<1280;pos+=20){
                     if(pos%500==0){
                         paint.setStrokeWidth(3);
                         paint.setColor(Color.parseColor("#8000ff00"));
                     }else if(pos%100==0){
                         paint.setStrokeWidth(1.5f);
                         paint.setColor(Color.parseColor("#80ff0000"));
                     }else{
                         paint.setColor(Color.parseColor("#80000000"));
                     }
                     cache.drawLine(0,pos,1280,pos,paint);
                     cache.drawLine(pos,0,pos,689,paint);
                     paint.setStrokeWidth(1);
                 }

        canvas.drawBitmap(buf,null,canvas.getClipBounds(),null);

    }
}
