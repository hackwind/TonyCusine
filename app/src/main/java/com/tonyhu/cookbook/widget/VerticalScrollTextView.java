package com.tonyhu.cookbook.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.tonyhu.cookbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class VerticalScrollTextView extends AppCompatTextView {

    private float step = 0f;
    private Paint mPaint=new Paint(); ;
    private String text;
    private float width;
    private List<String> textList = new ArrayList<String>();    //分行保存textview的显示信息。
    private int max = 20;

    public VerticalScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public VerticalScrollTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint.setTextSize(40f);//设置字体大小
        mPaint.setColor(getResources().getColor(R.color.main_title_normal));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException("ScrollLayout only canmCurScreen run at EXACTLY mode!");
        }

        text = getText().toString();
        if(text == null | text.length() == 0){

            return ;
        }

        //下面的代码是根据宽度和字体大小，来计算textview显示的行数。
        textList.clear();
        StringBuilder builder = null;
        float len = 0f;
        for(int i = 15;i < text.length();i++ ){
            len = mPaint.measureText(text.substring(0,i));
            if(len > width) {
                max = i - 1;
                break;
            }
        }
        for(int i = 0;i < text.length();i++){
            if(i % max == 0){
                builder = new StringBuilder();
            }
            if(i % max <= max - 1){
                builder.append(text.charAt(i));
            }
            if(i % max == max - 1){
                textList.add(builder.toString());
            }

        }


    }


    //下面代码是利用上面计算的显示行数，将文字画在画布上，实时更新。
    @Override
    public void onDraw(Canvas canvas) {
        if(textList.size() == 0)  return;

        for(int i = 0; i < textList.size(); i++) {
            canvas.drawText(textList.get(i), 0, this.getHeight() + (i + 1) * mPaint.getTextSize() - step, mPaint);
        }
        invalidate();

        step = step + 0.3f;
        if (step >= this.getHeight() + textList.size() * mPaint.getTextSize()) {
            step = 0;
        }
    }

}
