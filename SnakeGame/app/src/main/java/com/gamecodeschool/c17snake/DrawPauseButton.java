package com.gamecodeschool.c17snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;

public class DrawPauseButton extends SurfaceView {

    private SnakeGame snakeGame;

    public DrawPauseButton(Context context, SnakeGame snakeGame) {
        super(context);
        this.snakeGame = snakeGame;
    }

    public Point getScreenDimensions() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        return new Point(screenWidth, screenHeight);
    }

    public void drawButton(Canvas canvas, Paint paint) {
        // Set color for the button background
        paint.setColor(Color.argb(255, 203, 67, 53));

        // Get screen dimensions
        Point screenDimensions = getScreenDimensions();
        int screenWidth = screenDimensions.x;
        int screenHeight = screenDimensions.y;

        // Define the size and position of the button relative to screen dimensions
        int buttonWidth = screenWidth / 9;
        int buttonHeight = screenHeight / 20;
        int buttonLeft = (screenWidth - buttonWidth) / 2;
        int buttonTop = screenHeight / 10;

        // Draw a rounded rectangle representing the button
        canvas.drawRoundRect(
                buttonLeft, buttonTop,
                buttonLeft + buttonWidth, buttonTop + buttonHeight,
                25, 25, paint
        );

        // Set color and size for the button text
        paint.setColor(Color.WHITE);
        paint.setTextSize(buttonHeight * 0.9f);

        // Determine the text to be displayed based on the pause state
        String buttonText = snakeGame.isPaused() ? "Resume" : "Pause";

        // Calculate the position to center the text within the button
        float textWidth = paint.measureText(buttonText);
        float textX = buttonLeft + (buttonWidth - textWidth) / 2;
        float textY = buttonTop + (float) buttonHeight / 2 + paint.getTextSize() / 3; // Adjust for vertical centering

        // Draw the determined text at the center of the button
        canvas.drawText(buttonText, textX, textY, paint);
    }


}
