
package com.tonyhu.cookbook.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;

public class ScrollTextView extends android.support.v7.widget.AppCompatTextView {
    /**
     * ÿ�е��ַ���
     */
    ArrayList<String> lineStrings;

    /**
     * ��ǰ��λ��
     */
    float currentY;

    /**
     * ���������Ϣ
     */
    Handler handler;

    /**
     * Ҫ��ʾ��text
     */
    String scrollText="";
    

    /**
     * ��ʵ���,������width="xxdp"��������
     */
    private int exactlyWidth = -1;

    /**
     * ��ʵ�߶�,������height="xxdip"��������
     */
    private int exactlyHeight = -1;

    public String getScrollText() {
        return scrollText;
    }

    public void setScrollText(String scrollText) {
        this.scrollText = scrollText;
        reset();
    }

    /**
     * ����
     */
    private void reset() {
        requestLayout();
        invalidate();
        currentY = 0;

    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ScrollTextView(Context context) {
        super(context);
        init();
    }

    /**
     * �Ƿ��ڹ���
     */
    boolean scrolling = false;

    /**
     * ʵ�ʸ߶ȣ���������ʾ��ȫ��Ҫ�ĸ߶�
     */
    float absloutHeight = 0;

    /**
     * handler����Ϣ��ʱ����
     */
    int delayTime = 10;

    /**
     * ÿ�ι����ľ���
     */
    float speed = 0.5f;

    /**
     * ��ʼ��
     */
    void init() {

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (absloutHeight <= getHeight()) {
                    currentY = 0;
                    stop();
                    return;
                }
                switch (msg.what) {

                    case 0: {
                        currentY = currentY - speed;

                        resetCurrentY();
                        invalidate();
                        handler.sendEmptyMessageDelayed(0, delayTime);
                        break;
                    }
                    case 1: {

                        currentY += msg.arg1;

                        resetCurrentY();
                        invalidate();
                    }
                }

            }

            /**
             * ����currentY����currentY����absloutHeightʱ����������Ϊ0��
             */
            private void resetCurrentY() {
                if (currentY >= absloutHeight || currentY <= -absloutHeight || getHeight() <= 0) {
                    currentY = 0;
                }

            }
        };
        
    }

    /**
     * �ϴδ����¼�����ָy����
     */
    float lastY = 0;

    /**
     * Ϊtrue����ԭ���ǹ���
     */
    boolean needStop;

    public void pause() {
        if (scrolling) {

            stop();
            needStop = true;
        }
    }

    public void goOn() {

        if (needStop) {
            play();
            needStop = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                distanceY = lastY = event.getY();
                distanceX = event.getX();
                pause();

                return true;
            case MotionEvent.ACTION_MOVE:
                float dy = event.getY() - lastY;
                lastY = event.getY();
                // currentY = currentY + dy;
                Message msg = Message.obtain();
                msg.what = 1;
                msg.arg1 = (int)dy;
                handler.sendMessage(msg);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                goOn();
                float y = event.getY() - distanceY;
                float x = event.getX() - distanceX;

                if (Math.sqrt(y * y + x * x) < performUpScrollStateDistance) {
                    updateScrollStatus();
                }
                return true;

        }
        return super.onTouchEvent(event);
    }

    /**
     * ����ָ�ƶ��˴�С�ľ������ڣ�����Ϊ�ǹ���״̬�ı���¼������ڸ�ֵ��������ָ����
     */
    public static final long performUpScrollStateDistance = 5;

    public float distanceY = 0;

    public float distanceX = 0;

    /**
     * ���Ĺ���״̬
     */
    public void updateScrollStatus() {

        if (scrolling) {
            stop();
        } else {
            play();
        }
    }

    /**
     * ��ʼ����
     */
    public void play() {

        if (!scrolling) {
            handler.sendEmptyMessage(0);
            scrolling = true;
        }
    }

    /**
     * ֹͣ����
     */
    public void stop() {
        if (scrolling) {
            handler.removeMessages(0);
            scrolling = false;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureWidth(widthMeasureSpec);
        int height = MeasureHeight(width, heightMeasureSpec);
        setMeasuredDimension(width, height);
        currentY = 0;
        if (height < absloutHeight) {
            play();
        } else {
            stop();
        }

    }

    /**
     * �������
     * 
     * @param widthMeasureSpec
     * @return
     */
    private int MeasureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // �����wrap_content
        if (mode == MeasureSpec.AT_MOST) {

            double abwidth = getPaint().measureText(scrollText);

            width = Math.min((int)Math.rint(abwidth), width);
            exactlyWidth = -1;
        }
        if (mode == MeasureSpec.EXACTLY) {
            exactlyWidth = width;
        }
        return width;
    }

    /**
     * �����߶�
     * 
     * @param width:���
     * @param heightMeasureSpec
     * @return
     */
    private int MeasureHeight(int width, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        generateTextList(width);
        int lines = lineStrings.size();

        absloutHeight = lines * getLineHeight() + getPaddingBottom() + getPaddingTop();
        // �����wrap_content
        if (mode == MeasureSpec.AT_MOST) {

            height = (int)Math.min(absloutHeight, height);
            exactlyHeight = -1;

        } else if (mode == MeasureSpec.EXACTLY) {
            exactlyHeight = height;
        }
        return height;
    }

    /**
     * �Ƿ�ΪӢ�ĵ��ʵ�����ĸ
     * 
     * @param str
     * @param i
     * @return
     */
    boolean isENWordStart(String str, int i) {

        if (i == 0) {
            return true;

        } else if (str.charAt(i - 1) == ' ') {
            return true;
        }
        return false;
    }

    /**
     * ��ȡһ�е��ַ�
     * 
     * @param MaxWidth
     * @param str
     * @return
     */
    private String getLineText(int MaxWidth, String str) {

        // ��ʵ��
        StringBuffer trueStringBuffer = new StringBuffer();
        // ��ʱ��
        StringBuffer tempStringBuffer = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String add = "";
            // ���c����ĸ��Ҫ����Ӣ�ĵ��ʻ��й���
            if (!isChinese(c) && isENWordStart(str, i)) {

                int place = getNextSpecePlace(i, str);
                // �ҵ���һ���ո�
                if (place > -1) {
                    add = str.substring(i, place) + " ";
                    if (getPaint().measureText(add) > MaxWidth) {
                        add = "" + c;
                    } else {
                        i = place;
                    }
                } else {
                    add = "" + c;
                }
            } else {
                add = "" + c;
            }

            tempStringBuffer.append(add);
            String temp = tempStringBuffer.toString();
            float width = getPaint().measureText(temp.toString());

            if (width <= MaxWidth) {

                trueStringBuffer.append(add);
            } else {
                break;
            }

        }

        return trueStringBuffer.toString();

    }

    /**
     * �ҵ���һ���ո�ĵط�
     * 
     * @param i
     * @param str
     * @return
     */
    int getNextSpecePlace(int i, String str) {

        for (int j = i; j < str.length(); j++) {
            char c = str.charAt(j);
            if (c == ' ') {

                return j;
            }
        }
        return -1;
    }

    /**
     * ���ɶ����ַ����б�
     * 
     * @param MaxWidth
     */
    public void generateTextList(int MaxWidth) {
        lineStrings = new ArrayList<String>();
        String remain = scrollText;

        while (!remain.equals("")) {
            String line = getLineText(MaxWidth, remain);
            lineStrings.add(line);
            remain = remain.substring(line.length(), remain.length());

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        float x = getPaddingLeft();
        float y = getPaddingTop();

        float lineHeight = getLineHeight();
        float textSize = getPaint().getTextSize();

        for (int i = 0; i < lineStrings.size(); i++) {
            y = lineHeight * i + textSize + currentY;

            float min = 0;
            if (exactlyHeight > -1) {
                min = Math.min(min, exactlyHeight - absloutHeight);
            }
            if (y < min) {

                y = y + absloutHeight;

            } else if (y >= min && y < textSize + min) {

                //�����˵������Ѿ�������Ҫѭ�������������ʱ��
                canvas.drawText(lineStrings.get(i), x, y + absloutHeight, getPaint());
            }
            if (y >= absloutHeight) {
                //�����׶˵������Ѿ�������Ҫѭ�������������ʱ��
                canvas.drawText(lineStrings.get(i), x, y, getPaint());
                y = y - absloutHeight;
            }
            canvas.drawText(lineStrings.get(i), x, y, getPaint());
        }
    }

    /**
     * �ж��Ƿ�Ϊ����
     * 
     * @param c
     * @return
     */
    private static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

}
