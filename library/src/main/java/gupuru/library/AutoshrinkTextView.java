package gupuru.library;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;


public class AutoshrinkTextView extends TextView {

    //最小のテキストサイズ
    private static final float MIN_TEXT_SIZE = 6.0f;
    //テキスト幅計測用のペイント
    private Paint paint = new Paint();

    /**
     * コンストラクタ
     * @param context
     * @param attrs
     */
    public AutoshrinkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float measuredWidth = getMeasuredWidth();
        if (measuredWidth > 0) {
            shrinkTextSize();
        }

    }

    /**
     * テキストサイズを縮小
     */
    private void shrinkTextSize() {
        // テキストサイズを取得
        float tempTextSize = getTextSize();
        // テキスト幅が入りきっていない場合は、入るまで繰り返す
        while (getMeasuredWidth() < getTextWidth(tempTextSize)) {
            // テキストサイズを縮小
            tempTextSize--;

            if (tempTextSize <= MIN_TEXT_SIZE) {
                // 最小テキストサイズより小さくなった場合は、最小テキストサイズをセットして終了
                tempTextSize = MIN_TEXT_SIZE;
                break;
            }
        }

        // 調整した結果のテキストサイズをセット
        setTextSize(TypedValue.COMPLEX_UNIT_PX, tempTextSize);
    }

    /**
     * テキスト幅を取得
     * @param textSize
     * @return
     */
    private float getTextWidth(float textSize) {
        paint.setTextSize(textSize);
        return paint.measureText(getText().toString());
    }

}