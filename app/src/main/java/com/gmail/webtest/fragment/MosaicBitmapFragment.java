package com.gmail.webtest.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gmail.webtest.R;

public class MosaicBitmapFragment extends BaseFragment {
    private ImageView mosaicImageView;
    private Bitmap originalBitmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_mosaic_bitmap, container, false);

        mosaicImageView = view.findViewById(R.id.mosaic_imageview);
        originalBitmap = resizeToFitView(((BitmapDrawable)mosaicImageView.getDrawable()).getBitmap());

        final int[] mode = {0, 64};
        ((Button)view.findViewById(R.id.change_mode_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode[0] = 0;
            }
        });

        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                switch (mode[0]){
                    case 0:
                        mode[1] = (mode[1] + 1) % 16;
                        mosaicImageView.setImageBitmap(mosaic(originalBitmap, 16 - mode[1]));
                        break;
                }
                handler.postDelayed(this, 250);
            }
        });

        return view;
    }

    private Bitmap resizeToFitView(Bitmap beforeResizeBitmap){
        // リサイズ比
        double resizeScale;
        // 横長画像の場合
        if (beforeResizeBitmap.getWidth() >= beforeResizeBitmap.getHeight()) {
            resizeScale = (double) 1000 / beforeResizeBitmap.getWidth();
        }
        // 縦長画像の場合
        else {
            resizeScale = (double) 1000 / beforeResizeBitmap.getHeight();
        }
        // リサイズ
        Bitmap afterResizeBitmap = Bitmap.createScaledBitmap(beforeResizeBitmap,
                (int) (beforeResizeBitmap.getWidth() * resizeScale),
                (int) (beforeResizeBitmap.getHeight() * resizeScale),
                true);
        return afterResizeBitmap;
    }

    /**
     * モザイク加工(平均化)
     * @param originalBitmap
     * @param dot 荒さ
     * @return
     */
    private Bitmap mosaic(Bitmap originalBitmap, int dot){
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int square = dot * dot;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        // ピクセルデータ分ループ
        for(int w = 0, widthEnd = width / dot; w < widthEnd; w++){
            for(int h = 0, heightEnd = height / dot; h < heightEnd; h++){
                // ドットの中の平均値を使う
                int r = 0;
                int g = 0;
                int b = 0;
                int moveX = w * dot;
                int moveY = h * dot;
                for(int dw = 0; dw < dot; dw++){
                    for(int dh = 0; dh < dot; dh++){
                        int dotColor = pixels[moveX + dw + (moveY + dh) * width];
                        r += Color.red(dotColor);
                        g += Color.green(dotColor);
                        b += Color.blue(dotColor);
                    }
                }
                r = r / square;
                g = g / square;
                b = b / square;
                for(int dw = 0; dw < dot; dw++){
                    for(int dh = 0; dh < dot; dh++){
                        pixels[moveX + dw + (moveY + dh) * width] = Color.rgb(r, g, b);
                    }
                }
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return  bitmap;
    }
}
