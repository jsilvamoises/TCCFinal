package cc.unip.tccfinal.util;


import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MOISES
 */
public class IBackGround {
    private static final CornerRadii CORNER_10  = new CornerRadii(10, false);
    public static final Background  BACKGROUND_WHITE = new Background(new BackgroundFill(Color.WHITE, CORNER_10, Insets.EMPTY));
    public static final Background  BACKGROUND_DARKGREEN = new Background(new BackgroundFill(Color.DARKGREEN, CORNER_10, Insets.EMPTY));
    public static final Background  BACKGROUND_LIGHTSEAGREEN = new Background(new BackgroundFill(Color.LIGHTSEAGREEN, CORNER_10, Insets.EMPTY));
    public static final Background  BACKGROUND_TRANSPARENT = new Background(new BackgroundFill(Color.TRANSPARENT, CORNER_10, Insets.EMPTY));
    public static final Background  BACKGROUND_FIREBRICK = new Background(new BackgroundFill(Color.FIREBRICK, CORNER_10, Insets.EMPTY));
    public static final Background  BACKGROUND_MENU = new Background(new BackgroundFill(Color.DARKCYAN, CORNER_10, Insets.EMPTY));
    public static final Background  BACKGROUND_BLACK = new Background(new BackgroundFill(Color.BLACK, CORNER_10, Insets.EMPTY));
    public static final Background  BACKGROUND_RED = new Background(new BackgroundFill(Color.RED, CORNER_10, Insets.EMPTY));
    public static final Background  BACKGROUND_DARKCIAN = new Background(new BackgroundFill(Color.DARKCYAN, CORNER_10, Insets.EMPTY));
}
