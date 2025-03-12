package com.example.remotewifi;

import static com.google.android.material.internal.ViewUtils.dpToPx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;



public class SwitchCustomWithText extends SwitchCompat{

    private TextPaint textPaint;
    private String offText = "выкл";
    private String onText = "вкл";


    public SwitchCustomWithText(Context context) {
        super(context);
        init();
    }

    public SwitchCustomWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchCustomWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {   //Инициализируем Paint для рисования текста
        textPaint = new TextPaint();
//        textPaint.setAntiAlias(true); // Сглаживание текста
        textPaint.setTextSize(45); // Устанавливаем размер текста
//        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
    }


    @Override
    protected void onDraw(Canvas canvas) {  //Переопределяем метод рисования, чтобы добавить текст поверх стандартного Switch
        super.onDraw(canvas); // Рисуем стандартный Switch

        // Получаем состояние переключателя
        boolean isChecked = isChecked();

        // Настраиваем цвет и позицию текста
        if (isChecked) {
            textPaint.setColor(Color.BLACK); // Черный текст для "ВКЛ"

            drawOnText(canvas);
        } else {
            textPaint.setColor(Color.WHITE); // Белый текст для "ВЫКЛ"
            drawOffText(canvas);
        }
    }


    private void drawOnText(Canvas canvas) {
        // Получаем размеры текста
        float textWidth = textPaint.measureText(onText);
        float textHeight = textPaint.getTextSize();

        // Получаем размеры SwitchCompat
        int switchWidth = getWidth();
        int switchHeight = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        // Примерная ширина и высота track (примерно 2/3 ширины Switch)
        float trackWidth = switchWidth - paddingLeft - paddingRight;
        float trackHeight = switchHeight - paddingTop - paddingBottom;

        // Получаем границы track (примерно)
        float trackLeft = paddingLeft + (switchWidth - trackWidth) / 2;
        float trackRight = trackLeft + trackWidth;
        float trackTop = paddingTop + (switchHeight - trackHeight) / 2;
        float trackBottom = trackTop + trackHeight;

        // Позиция текста "ВКЛ" (слева внутри track)
        float textX = trackLeft + (trackWidth / 4) - (textWidth / 2); // Сдвигаем влево
        float textY = trackTop + (trackHeight + textHeight) / 2; // Центрируем по вертикали

        canvas.drawText(onText, textX, textY, textPaint);
    }

    private void drawOffText(Canvas canvas) {
        // Получаем размеры текста
        float textWidth = textPaint.measureText(offText);
        float textHeight = textPaint.getTextSize();

        // Получаем размеры SwitchCompat
        int switchWidth = getWidth();
        int switchHeight = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        // Примерная ширина и высота track
        float trackWidth = switchWidth - paddingLeft - paddingRight;
        float trackHeight = switchHeight - paddingTop - paddingBottom;

        // Получаем границы track
        float trackLeft = paddingLeft + (switchWidth - trackWidth) / 2;
        float trackRight = trackLeft + trackWidth;
        float trackTop = paddingTop + (switchHeight - trackHeight) / 2;
        float trackBottom = trackTop + trackHeight;

        // Позиция текста "ВЫКЛ" (справа внутри track)
        float textX = trackRight - (trackWidth / 4) - textWidth; // Сдвигаем вправо
        float textY = trackTop + (trackHeight + textHeight) / 2; // Центрируем по вертикали

        canvas.drawText(offText, textX, textY, textPaint);
    }

}
