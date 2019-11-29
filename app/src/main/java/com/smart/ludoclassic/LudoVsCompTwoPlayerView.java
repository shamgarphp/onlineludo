package com.smart.ludoclassic;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LudoVsCompTwoPlayerView extends View {
    static Integer START_PATH = Integer.valueOf(0);
    ArrayList<DrawableMarker> activeMarkersList;
    Bitmap bgd;
    int blink;
    int blinkMarker;
    HashMap<Integer, GridBox> boxHashMap;
    int boxesMarkerWillMove;
    ArrayList<GridBox> boxesToVisitArray;
    ArrayList<Paint> colorList;
    ArrayList<Paint> colorListBlink;
    int[][] colorPaths;
    Drawable comp1;
    Drawable comp2;
    Drawable comp3;
    Paint compPositionPaint;
    private Context context;
    Integer[] currentBoxPlayer;
    Integer currentPlayer;
    private DelayHandler delayHandler;
    ArrayList<Integer> destHomeList;
    Paint diceBoxPaint;
    RectF[] diceBoxRectFs;
    LudoDiceBox[] diceBoxes;
    Boolean diceReadyForClick;
    List<HashMap<Integer, Drawable>> diceSidesMapList;
    Integer diceValue;
    Boolean diceValuePreviousWasSix;
    Integer f54k;
    Integer f55l;
    Boolean gameComplete;
    long gameLoopDelay;
    private GameLoopHandler gameLoopHandler;
    Paint gameOverPaint;
    int[] gameOverRectangle;
    int gameOverSize;
    float gameOverStrokeWidth;
    public Integer gameState;
    float gridBoxDpHeight;
    float gridBoxDpWidth;
    Paint gridLinesPaint;
    int[][] homeBoxsInner;
    int[][] homeBoxsOuter;
    int[][] homeCircles;
    int[][] homeTriangles;
    Boolean landedOnOpponentsToken;
    long longDiceDelay;
    long mDiceDelay;
    long mDiceRollDelay;
    long mMoveDelay;
    Drawable mRes;
    Drawable[] markerAdjecentDiceBox;
    LudoDiceBox[] markerBoxes;
    long markerClickDelay;
    Boolean markerReadyForClick;
    Drawable[][] markersInGame;
    List<Drawable> markersInGameList;
    Integer maxDiceRolls;
    Paint outLinesPaintBlue;
    Paint outLinesPaintGreen;
    Paint outLinesPaintRed;
    Paint outLinesPaintYellow;
    Paint paintBlue;
    Paint paintBlueBlink;
    Paint paintGreen;
    Paint paintGreenBlink;
    Paint paintRed;
    Paint paintRedBlink;
    Paint paintWhite;
    Paint paintYellow;
    Paint paintYellowBlink;
    ArrayList<LudoPlayer> playerList;
    LudoPlayer playerNow;
    Integer[] playerOutOfGame;
    int[][] playerPath;
    Paint playerPositionPaint;
    ArrayList<LudoPlayer> playerRankList;
    DrawableMarker playerRockToMove;
    Integer players;
    int rankNoSize;
    float rankNoStrokeWidth;
    Paint rankingPaint;
    Boolean reachedHome;
    String[] rockPaintColors;
    Paint[] rockPaintListFill;
    Paint[] rockPaintListStroke;
    int[] safeBoxes;
    Map<Integer, Integer> safeBoxesMap;
    int[] safeStarBoxes;
    Drawable[] safeStars;
    ArrayList<Integer> snakeOrLadderPath;
    long switchToCompDelay;
    HashMap<Integer, ArrayList<TakenBox>> takenBoxMap;
    Paint textColorPaint;
    Paint textColorPaint2;
    Path[] trianglePathList;
    Integer winner;
    Paint winsPaintFill;
    Paint winsPaintStrok;
    Drawable youImage;
    Integer youPlayerId;

    class C03931 implements Comparator<Drawable> {
        C03931() {
        }

        public int compare(Drawable arg0, Drawable arg1) {
            return arg0.getBounds().centerY() - arg1.getBounds().centerY();
        }
    }

    class DelayHandler extends Handler {
        DelayHandler() {
        }

        public void handleMessage(Message msg) {
            LudoVsCompTwoPlayerView.this.play();
        }

        public void sleep(long delayMillis) {
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }

    class GameLoopHandler extends Handler {
        GameLoopHandler() {
        }

        public void handleMessage(Message msg) {
            LudoVsCompTwoPlayerView.this.invalidate();
            LudoVsCompTwoPlayerView.this.gameLoop();
        }

        public void sleep(long delayMillis) {
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }

    public LudoVsCompTwoPlayerView(Context context) {
        super(context);
        this.currentBoxPlayer = new Integer[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)};
        this.playerOutOfGame = new Integer[]{Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0)};
        this.playerRankList = new ArrayList();
        this.rockPaintColors = new String[]{"YELLOW", "GREEN", "RED", "BLUE"};
        this.rockPaintListFill = new Paint[4];
        this.rockPaintListStroke = new Paint[4];
        this.boxHashMap = new HashMap();
        this.takenBoxMap = new HashMap();
        this.diceBoxes = new LudoDiceBox[4];
        this.markerBoxes = new LudoDiceBox[4];
        this.diceBoxRectFs = new RectF[4];
        this.homeBoxsOuter = new int[][]{new int[]{1, 81}, new int[]{10, 90}, new int[]{145, 225}, new int[]{136, 216}};
        this.homeBoxsInner = new int[][]{new int[]{17, 65}, new int[]{26, 74}, new int[]{161, 209}, new int[]{152, 200}};
        this.homeCircles = new int[][]{new int[]{33, 35, 65, 63}, new int[]{42, 44, 74, 72}, new int[]{177, 179, 209, 207}, new int[]{168, 170, 200, 198}};
        this.safeBoxes = new int[]{24, 134, 202, 92, 123, 37, 103, 189};
        this.safeStarBoxes = new int[]{123, 37, 103, 189};
        this.safeBoxesMap = new HashMap();
        this.gameOverRectangle = new int[]{95, 131};
        this.boxesMarkerWillMove = 0;
        this.activeMarkersList = new ArrayList();
        this.playerPath = new int[][]{new int[]{92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 107, 108, 109, 110, 111, 112}, new int[]{24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 23, 38, 53, 68, 83, 98}, new int[]{134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 119, 118, 117, 116, 115, 114}, new int[]{202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 203, 188, 173, 158, 143, 128}};
        this.homeTriangles = new int[][]{new int[]{97, 141, 113}, new int[]{97, 99, 113}, new int[]{100, 144, 113}, new int[]{142, 144, 113}};
        this.trianglePathList = new Path[4];
        this.colorPaths = new int[][]{new int[]{92, 107, 108, 109, 110, 111}, new int[]{24, 23, 38, 53, 68, 83}, new int[]{134, 119, 118, 117, 116, 115}, new int[]{202, 203, 188, 173, 158, 143}};
        this.colorList = new ArrayList();
        this.colorListBlink = new ArrayList();
        this.markerAdjecentDiceBox = new Drawable[4];
        this.markersInGame = (Drawable[][]) Array.newInstance(Drawable.class, new int[]{4, 4});
        this.safeStars = new Drawable[4];
        this.playerRockToMove = null;
        this.players = Integer.valueOf(4);
        this.currentPlayer = Integer.valueOf(0);
        this.winner = Integer.valueOf(0);
        this.gridBoxDpHeight = 0.0f;
        this.gridBoxDpWidth = 0.0f;
        this.f54k = Integer.valueOf(0);
        this.f55l = Integer.valueOf(0);
        this.maxDiceRolls = Integer.valueOf(0);
        this.diceValue = Integer.valueOf(1);
        this.gameLoopDelay = 100;
        this.mMoveDelay = 400;
        this.mDiceDelay = 300;
        this.longDiceDelay = 500;
        this.mDiceRollDelay = 200;
        this.markerClickDelay = 200;
        this.switchToCompDelay = 1200;
        this.blink = 0;
        this.blinkMarker = 0;
        this.diceSidesMapList = new ArrayList();
        this.markerReadyForClick = Boolean.valueOf(false);
        this.diceReadyForClick = Boolean.valueOf(true);
        this.gameComplete = Boolean.valueOf(false);
        this.diceValuePreviousWasSix = Boolean.valueOf(false);
        this.landedOnOpponentsToken = Boolean.valueOf(false);
        this.reachedHome = Boolean.valueOf(false);
        this.rankNoStrokeWidth = 0.0f;
        this.rankNoSize = 0;
        this.gameOverStrokeWidth = 0.0f;
        this.gameOverSize = 0;
        this.delayHandler = new DelayHandler();
        this.gameLoopHandler = new GameLoopHandler();
        this.gameState = Integer.valueOf(0);
        this.context = context;
        init();
    }

    public LudoVsCompTwoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.currentBoxPlayer = new Integer[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)};
        this.playerOutOfGame = new Integer[]{Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0)};
        this.playerRankList = new ArrayList();
        this.rockPaintColors = new String[]{"YELLOW", "GREEN", "RED", "BLUE"};
        this.rockPaintListFill = new Paint[4];
        this.rockPaintListStroke = new Paint[4];
        this.boxHashMap = new HashMap();
        this.takenBoxMap = new HashMap();
        this.diceBoxes = new LudoDiceBox[4];
        this.markerBoxes = new LudoDiceBox[4];
        this.diceBoxRectFs = new RectF[4];
        this.homeBoxsOuter = new int[][]{new int[]{1, 81}, new int[]{10, 90}, new int[]{145, 225}, new int[]{136, 216}};
        this.homeBoxsInner = new int[][]{new int[]{17, 65}, new int[]{26, 74}, new int[]{161, 209}, new int[]{152, 200}};
        this.homeCircles = new int[][]{new int[]{33, 35, 65, 63}, new int[]{42, 44, 74, 72}, new int[]{177, 179, 209, 207}, new int[]{168, 170, 200, 198}};
        this.safeBoxes = new int[]{24, 134, 202, 92, 123, 37, 103, 189};
        this.safeStarBoxes = new int[]{123, 37, 103, 189};
        this.safeBoxesMap = new HashMap();
        this.gameOverRectangle = new int[]{95, 131};
        this.boxesMarkerWillMove = 0;
        this.activeMarkersList = new ArrayList();
        this.playerPath = new int[][]{new int[]{92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 107, 108, 109, 110, 111, 112}, new int[]{24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 23, 38, 53, 68, 83, 98}, new int[]{134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 119, 118, 117, 116, 115, 114}, new int[]{202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 203, 188, 173, 158, 143, 128}};
        this.homeTriangles = new int[][]{new int[]{97, 141, 113}, new int[]{97, 99, 113}, new int[]{100, 144, 113}, new int[]{142, 144, 113}};
        this.trianglePathList = new Path[4];
        this.colorPaths = new int[][]{new int[]{92, 107, 108, 109, 110, 111}, new int[]{24, 23, 38, 53, 68, 83}, new int[]{134, 119, 118, 117, 116, 115}, new int[]{202, 203, 188, 173, 158, 143}};
        this.colorList = new ArrayList();
        this.colorListBlink = new ArrayList();
        this.markerAdjecentDiceBox = new Drawable[4];
        this.markersInGame = (Drawable[][]) Array.newInstance(Drawable.class, new int[]{4, 4});
        this.safeStars = new Drawable[4];
        this.playerRockToMove = null;
        this.players = Integer.valueOf(4);
        this.currentPlayer = Integer.valueOf(0);
        this.winner = Integer.valueOf(0);
        this.gridBoxDpHeight = 0.0f;
        this.gridBoxDpWidth = 0.0f;
        this.f54k = Integer.valueOf(0);
        this.f55l = Integer.valueOf(0);
        this.maxDiceRolls = Integer.valueOf(0);
        this.diceValue = Integer.valueOf(1);
        this.gameLoopDelay = 100;
        this.mMoveDelay = 400;
        this.mDiceDelay = 300;
        this.longDiceDelay = 500;
        this.mDiceRollDelay = 200;
        this.markerClickDelay = 200;
        this.switchToCompDelay = 1200;
        this.blink = 0;
        this.blinkMarker = 0;
        this.diceSidesMapList = new ArrayList();
        this.markerReadyForClick = Boolean.valueOf(false);
        this.diceReadyForClick = Boolean.valueOf(true);
        this.gameComplete = Boolean.valueOf(false);
        this.diceValuePreviousWasSix = Boolean.valueOf(false);
        this.landedOnOpponentsToken = Boolean.valueOf(false);
        this.reachedHome = Boolean.valueOf(false);
        this.rankNoStrokeWidth = 0.0f;
        this.rankNoSize = 0;
        this.gameOverStrokeWidth = 0.0f;
        this.gameOverSize = 0;
        this.delayHandler = new DelayHandler();
        this.gameLoopHandler = new GameLoopHandler();
        this.gameState = Integer.valueOf(0);
        this.context = context;
        init();
    }

    public LudoVsCompTwoPlayerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.currentBoxPlayer = new Integer[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)};
        this.playerOutOfGame = new Integer[]{Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0)};
        this.playerRankList = new ArrayList();
        this.rockPaintColors = new String[]{"YELLOW", "GREEN", "RED", "BLUE"};
        this.rockPaintListFill = new Paint[4];
        this.rockPaintListStroke = new Paint[4];
        this.boxHashMap = new HashMap();
        this.takenBoxMap = new HashMap();
        this.diceBoxes = new LudoDiceBox[4];
        this.markerBoxes = new LudoDiceBox[4];
        this.diceBoxRectFs = new RectF[4];
        this.homeBoxsOuter = new int[][]{new int[]{1, 81}, new int[]{10, 90}, new int[]{145, 225}, new int[]{136, 216}};
        this.homeBoxsInner = new int[][]{new int[]{17, 65}, new int[]{26, 74}, new int[]{161, 209}, new int[]{152, 200}};
        this.homeCircles = new int[][]{new int[]{33, 35, 65, 63}, new int[]{42, 44, 74, 72}, new int[]{177, 179, 209, 207}, new int[]{168, 170, 200, 198}};
        this.safeBoxes = new int[]{24, 134, 202, 92, 123, 37, 103, 189};
        this.safeStarBoxes = new int[]{123, 37, 103, 189};
        this.safeBoxesMap = new HashMap();
        this.gameOverRectangle = new int[]{95, 131};
        this.boxesMarkerWillMove = 0;
        this.activeMarkersList = new ArrayList();
        this.playerPath = new int[][]{new int[]{92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 107, 108, 109, 110, 111, 112}, new int[]{24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 23, 38, 53, 68, 83, 98}, new int[]{134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 217, 202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 119, 118, 117, 116, 115, 114}, new int[]{202, 187, 172, 157, 142, 126, 125, 124, 123, 122, 121, 106, 91, 92, 93, 94, 95, 96, 82, 67, 52, 37, 22, 7, 8, 9, 24, 39, 54, 69, 84, 100, 101, 102, 103, 104, 105, 120, 135, 134, 133, 132, 131, 130, 144, 159, 174, 189, 204, 219, 218, 203, 188, 173, 158, 143, 128}};
        this.homeTriangles = new int[][]{new int[]{97, 141, 113}, new int[]{97, 99, 113}, new int[]{100, 144, 113}, new int[]{142, 144, 113}};
        this.trianglePathList = new Path[4];
        this.colorPaths = new int[][]{new int[]{92, 107, 108, 109, 110, 111}, new int[]{24, 23, 38, 53, 68, 83}, new int[]{134, 119, 118, 117, 116, 115}, new int[]{202, 203, 188, 173, 158, 143}};
        this.colorList = new ArrayList();
        this.colorListBlink = new ArrayList();
        this.markerAdjecentDiceBox = new Drawable[4];
        this.markersInGame = (Drawable[][]) Array.newInstance(Drawable.class, new int[]{4, 4});
        this.safeStars = new Drawable[4];
        this.playerRockToMove = null;
        this.players = Integer.valueOf(4);
        this.currentPlayer = Integer.valueOf(0);
        this.winner = Integer.valueOf(0);
        this.gridBoxDpHeight = 0.0f;
        this.gridBoxDpWidth = 0.0f;
        this.f54k = Integer.valueOf(0);
        this.f55l = Integer.valueOf(0);
        this.maxDiceRolls = Integer.valueOf(0);
        this.diceValue = Integer.valueOf(1);
        this.gameLoopDelay = 100;
        this.mMoveDelay = 400;
        this.mDiceDelay = 300;
        this.longDiceDelay = 500;
        this.mDiceRollDelay = 200;
        this.markerClickDelay = 200;
        this.switchToCompDelay = 1200;
        this.blink = 0;
        this.blinkMarker = 0;
        this.diceSidesMapList = new ArrayList();
        this.markerReadyForClick = Boolean.valueOf(false);
        this.diceReadyForClick = Boolean.valueOf(true);
        this.gameComplete = Boolean.valueOf(false);
        this.diceValuePreviousWasSix = Boolean.valueOf(false);
        this.landedOnOpponentsToken = Boolean.valueOf(false);
        this.reachedHome = Boolean.valueOf(false);
        this.rankNoStrokeWidth = 0.0f;
        this.rankNoSize = 0;
        this.gameOverStrokeWidth = 0.0f;
        this.gameOverSize = 0;
        this.delayHandler = new DelayHandler();
        this.gameLoopHandler = new GameLoopHandler();
        this.gameState = Integer.valueOf(0);
        this.context = context;
        init();
    }

    public void init() {
        int i;
        int row;
        Resources res = getResources();
        this.rankNoStrokeWidth = res.getDimension(R.dimen.ludo_rank_stroke_width);
        this.rankNoSize = res.getDimensionPixelSize(R.dimen.ludo_rank_size);
        this.gameOverStrokeWidth = res.getDimension(R.dimen.ludo_game_over_width);
        this.gameOverSize = res.getDimensionPixelSize(R.dimen.ludo_game_over_size);
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        float dpWidth = (float) displayMetrics.widthPixels;
        this.gridBoxDpHeight = ((float) displayMetrics.heightPixels) / 27.0f;
        this.gridBoxDpWidth = dpWidth / 15.45f;
        this.destHomeList = new ArrayList();
        this.destHomeList.add(Integer.valueOf(this.playerPath[0].length - 1));
        this.boxesToVisitArray = new ArrayList();
        Integer tag = Integer.valueOf(1);
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                GridBox gridBox = new GridBox((float) x, ((float) y) + 4.5f, tag.intValue());
                this.boxesToVisitArray.add(gridBox);
                this.boxHashMap.put(tag, gridBox);
                tag = Integer.valueOf(tag.intValue() + 1);
            }
        }
        for (i = 0; i < 4; i++) {
            int[] triangle = this.homeTriangles[i];
            GridBox gridBox0 = (GridBox) this.boxHashMap.get(Integer.valueOf(triangle[0]));
            GridBox gridBox1 = (GridBox) this.boxHashMap.get(Integer.valueOf(triangle[1]));
            GridBox gridBox2 = (GridBox) this.boxHashMap.get(Integer.valueOf(triangle[2]));
            Path path = new Path();
            path.setFillType(FillType.EVEN_ODD);
            path.moveTo(gridBox0.getX() * this.gridBoxDpWidth, gridBox0.getY() * this.gridBoxDpHeight);
            path.lineTo((gridBox1.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, gridBox1.getY() * this.gridBoxDpHeight);
            path.lineTo((gridBox2.getX() * this.gridBoxDpWidth) + (this.gridBoxDpWidth / 2.0f), (gridBox2.getY() * this.gridBoxDpHeight) + (this.gridBoxDpHeight / 2.0f));
            path.lineTo(gridBox0.getX() * this.gridBoxDpWidth, gridBox0.getY() * this.gridBoxDpHeight);
            path.close();
            this.trianglePathList[i] = path;
        }
        float yOffsetTop = -1.7f * this.gridBoxDpHeight;
        float yOffsetBottom = 1.7f * this.gridBoxDpHeight;
        GridBox topLeftGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(4));
        GridBox topRightGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(12));
        GridBox bottomRightGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(222));
        GridBox bottomLeftGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(214));
        this.diceBoxes[0] = new LudoDiceBox(topLeftGridBox.getX() * this.gridBoxDpWidth, (topLeftGridBox.getY() * this.gridBoxDpHeight) + yOffsetTop, (topLeftGridBox.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, ((topLeftGridBox.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight) + yOffsetTop);
        this.diceBoxes[1] = new LudoDiceBox(topRightGridBox.getX() * this.gridBoxDpWidth, (topRightGridBox.getY() * this.gridBoxDpHeight) + yOffsetTop, (topRightGridBox.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, ((topRightGridBox.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight) + yOffsetTop);
        this.diceBoxes[2] = new LudoDiceBox(bottomRightGridBox.getX() * this.gridBoxDpWidth, (bottomRightGridBox.getY() * this.gridBoxDpHeight) + yOffsetBottom, (bottomRightGridBox.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, ((bottomRightGridBox.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight) + yOffsetBottom);
        this.diceBoxes[3] = new LudoDiceBox(bottomLeftGridBox.getX() * this.gridBoxDpWidth, (bottomLeftGridBox.getY() * this.gridBoxDpHeight) + yOffsetBottom, (bottomLeftGridBox.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, ((bottomLeftGridBox.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight) + yOffsetBottom);
        int[] iArr = new int[2];
        Drawable[][] diceSides = (Drawable[][]) Array.newInstance(Drawable.class, new int[]{4, 6});
        for (row = 0; row < 4; row++) {
            diceSides[row][0] = this.context.getResources().getDrawable(R.drawable.dice1);
            diceSides[row][1] = this.context.getResources().getDrawable(R.drawable.dice2);
            diceSides[row][2] = this.context.getResources().getDrawable(R.drawable.dice3);
            diceSides[row][3] = this.context.getResources().getDrawable(R.drawable.dice4);
            diceSides[row][4] = this.context.getResources().getDrawable(R.drawable.dice5);
            diceSides[row][5] = this.context.getResources().getDrawable(R.drawable.dice6);
        }
        for (row = 0; row < 4; row++) {
            for (int col = 0; col < 6; col++) {
                diceSides[row][col].setBounds((int) (this.diceBoxes[row].x1 - (this.gridBoxDpWidth / 4.0f)), (int) (this.diceBoxes[row].y1 - (this.gridBoxDpHeight / 4.0f)), (int) (this.diceBoxes[row].x2 + (this.gridBoxDpWidth / 4.0f)), (int) (this.diceBoxes[row].y2 + (this.gridBoxDpHeight / 4.0f)));
            }
        }
        for (row = 0; row < 4; row++) {
            HashMap<Integer, Drawable> diceSidesMap = new HashMap();
            diceSidesMap.put(Integer.valueOf(1), diceSides[row][0]);
            diceSidesMap.put(Integer.valueOf(2), diceSides[row][1]);
            diceSidesMap.put(Integer.valueOf(3), diceSides[row][2]);
            diceSidesMap.put(Integer.valueOf(4), diceSides[row][3]);
            diceSidesMap.put(Integer.valueOf(5), diceSides[row][4]);
            diceSidesMap.put(Integer.valueOf(6), diceSides[row][5]);
            this.diceSidesMapList.add(diceSidesMap);
        }
        GridBox topLeftGridBoxMarker = (GridBox) this.boxHashMap.get(Integer.valueOf(2));
        GridBox topRightGridBoxMarker = (GridBox) this.boxHashMap.get(Integer.valueOf(14));
        GridBox bottomRightGridMarker = (GridBox) this.boxHashMap.get(Integer.valueOf(224));
        GridBox bottomLeftGridBoxMarker = (GridBox) this.boxHashMap.get(Integer.valueOf(212));
        this.markerBoxes[0] = new LudoDiceBox(topLeftGridBoxMarker.getX() * this.gridBoxDpWidth, (topLeftGridBoxMarker.getY() * this.gridBoxDpHeight) + yOffsetTop, (topLeftGridBoxMarker.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, ((topLeftGridBoxMarker.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight) + yOffsetTop);
        this.markerBoxes[1] = new LudoDiceBox(topRightGridBoxMarker.getX() * this.gridBoxDpWidth, (topRightGridBoxMarker.getY() * this.gridBoxDpHeight) + yOffsetTop, (topRightGridBoxMarker.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, ((topRightGridBoxMarker.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight) + yOffsetTop);
        this.markerBoxes[2] = new LudoDiceBox(bottomRightGridMarker.getX() * this.gridBoxDpWidth, (bottomRightGridMarker.getY() * this.gridBoxDpHeight) + yOffsetBottom, (bottomRightGridMarker.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, ((bottomRightGridMarker.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight) + yOffsetBottom);
        this.markerBoxes[3] = new LudoDiceBox(bottomLeftGridBoxMarker.getX() * this.gridBoxDpWidth, (bottomLeftGridBoxMarker.getY() * this.gridBoxDpHeight) + yOffsetBottom, (bottomLeftGridBoxMarker.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, ((bottomLeftGridBoxMarker.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight) + yOffsetBottom);
        this.markerAdjecentDiceBox[0] = this.context.getResources().getDrawable(R.drawable.marker_yellow);
        this.markerAdjecentDiceBox[1] = this.context.getResources().getDrawable(R.drawable.marker_green);
        this.markerAdjecentDiceBox[2] = this.context.getResources().getDrawable(R.drawable.marker_red);
        this.markerAdjecentDiceBox[3] = this.context.getResources().getDrawable(R.drawable.marker_blue);
        for (row = 0; row < 4; row++) {
            this.markerAdjecentDiceBox[row].setBounds((int) (this.markerBoxes[row].x1 - (this.gridBoxDpWidth / 2.0f)), (int) (this.markerBoxes[row].y1 - (this.gridBoxDpHeight / 2.0f)), (int) (this.markerBoxes[row].x2 + (this.gridBoxDpWidth / 2.0f)), (int) (this.markerBoxes[row].y2 + (this.gridBoxDpHeight / 2.0f)));
        }
        for (row = 0; row < 4; row++) {
            this.diceBoxRectFs[row] = new RectF(this.diceBoxes[row].x1 - (this.gridBoxDpWidth / 2.0f), this.diceBoxes[row].y1 - (this.gridBoxDpHeight / 2.0f), this.diceBoxes[row].x2 + (this.gridBoxDpWidth / 2.0f), this.diceBoxes[row].y2 + (this.gridBoxDpHeight / 2.0f));
        }
        this.playerList = new ArrayList();
        this.playerList.add(new LudoPlayer(0, "", true, ""));
        this.playerList.add(new LudoPlayer(1, "", true, ""));
        this.playerList.add(new LudoPlayer(2, "", true, ""));
        this.playerList.add(new LudoPlayer(3, "", false, "BLUE"));
        this.playerNow = (LudoPlayer) this.playerList.get(3);
        this.youPlayerId = Integer.valueOf(3);
        for (i = 1; i < 4; i += 2) {
            this.playerOutOfGame[i] = Integer.valueOf(0);
        }
        for (i = 0; i < 4; i++) {
            this.safeStars[i] = this.context.getResources().getDrawable(R.drawable.star).mutate();
            GridBox yellowGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.safeStarBoxes[i]));
            this.safeStars[i].setBounds((int) (yellowGridBox.getX() * this.gridBoxDpWidth), (int) (yellowGridBox.getY() * this.gridBoxDpHeight), (int) ((yellowGridBox.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth), (int) ((yellowGridBox.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight));
        }
        for (i = 0; i < this.safeBoxes.length; i++) {
            this.safeBoxesMap.put(Integer.valueOf(this.safeBoxes[i]), Integer.valueOf(i));
        }
        initializeMarkersInGame();
        initYouOrCompImages();
        definePaintColors();
        gameLoop();
    }

    private void initYouOrCompImages() {
        this.youImage = this.context.getResources().getDrawable(R.drawable.you).mutate();
        GridBox youBox = (GridBox) this.boxHashMap.get(Integer.valueOf(213));
        this.youImage.setBounds((int) (youBox.getX() * this.gridBoxDpWidth), (int) (youBox.getY() * this.gridBoxDpHeight), (int) ((youBox.getX() * this.gridBoxDpWidth) + (this.gridBoxDpWidth * 2.0f)), (int) ((youBox.getY() * this.gridBoxDpHeight) + (this.gridBoxDpHeight * 0.7f)));
        this.comp1 = this.context.getResources().getDrawable(R.drawable.comp).mutate();
        GridBox compBox1 = (GridBox) this.boxHashMap.get(Integer.valueOf(3));
        this.comp1.setBounds((int) (compBox1.getX() * this.gridBoxDpWidth), (int) (compBox1.getY() * this.gridBoxDpHeight), (int) ((compBox1.getX() * this.gridBoxDpWidth) + (this.gridBoxDpWidth * 2.0f)), (int) ((compBox1.getY() * this.gridBoxDpHeight) + (this.gridBoxDpHeight * 0.7f)));
        this.comp2 = this.context.getResources().getDrawable(R.drawable.comp).mutate();
        GridBox compBox2 = (GridBox) this.boxHashMap.get(Integer.valueOf(12));
        this.comp2.setBounds((int) (compBox2.getX() * this.gridBoxDpWidth), (int) (compBox2.getY() * this.gridBoxDpHeight), (int) ((compBox2.getX() * this.gridBoxDpWidth) + (this.gridBoxDpWidth * 2.0f)), (int) ((compBox2.getY() * this.gridBoxDpHeight) + (this.gridBoxDpHeight * 0.7f)));
        this.comp3 = this.context.getResources().getDrawable(R.drawable.comp).mutate();
        GridBox compBox3 = (GridBox) this.boxHashMap.get(Integer.valueOf(222));
        this.comp3.setBounds((int) (compBox3.getX() * this.gridBoxDpWidth), (int) (compBox3.getY() * this.gridBoxDpHeight), (int) ((compBox3.getX() * this.gridBoxDpWidth) + (this.gridBoxDpWidth * 2.0f)), (int) ((compBox3.getY() * this.gridBoxDpHeight) + (this.gridBoxDpHeight * 0.7f)));
    }

    private void initializeMarkersInGame() {
        int col;
        for (col = 0; col < 4; col++) {
            this.markersInGame[0][col] = this.context.getResources().getDrawable(R.drawable.marker_yellow).mutate();
            GridBox yellowGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.homeCircles[0][col]));
            this.markersInGame[0][col].setBounds((int) ((yellowGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((yellowGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)), (int) ((yellowGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((yellowGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)));
            this.markersInGame[1][col] = this.context.getResources().getDrawable(R.drawable.marker_green).mutate();
            GridBox greenGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.homeCircles[1][col]));
            this.markersInGame[1][col].setBounds((int) ((greenGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((greenGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)), (int) ((greenGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((greenGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)));
            this.markersInGame[2][col] = this.context.getResources().getDrawable(R.drawable.marker_red).mutate();
            GridBox redGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.homeCircles[2][col]));
            this.markersInGame[2][col].setBounds((int) ((redGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((redGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)), (int) ((redGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((redGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)));
            this.markersInGame[3][col] = this.context.getResources().getDrawable(R.drawable.marker_blue).mutate();
            GridBox blueGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.homeCircles[3][col]));
            this.markersInGame[3][col].setBounds((int) ((blueGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((blueGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)), (int) ((blueGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((blueGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)));
        }
        this.markersInGameList = new ArrayList();
        for (int row = 1; row < 4; row += 2) {
            for (col = 0; col < 4; col++) {
                this.markersInGameList.add(this.markersInGame[row][col]);
            }
        }
    }

    private void definePaintColors() {
        this.gridLinesPaint = new Paint();
        this.gridLinesPaint.setStrokeWidth(1.0f);
        this.gridLinesPaint.setStyle(Style.STROKE);
        this.gridLinesPaint.setColor(Color.parseColor("#757575"));
        this.gridLinesPaint.setFlags(1);
        this.paintWhite = new Paint();
        this.paintWhite.setStrokeWidth(6.0f);
        this.paintWhite.setStyle(Style.FILL);
        this.paintWhite.setColor(-1);
        this.paintWhite.setFlags(1);
        this.paintYellow = new Paint();
        this.paintYellow.setStrokeWidth(6.0f);
        this.paintYellow.setStyle(Style.FILL);
        this.paintYellow.setColor(Color.parseColor("#ffc107"));
        this.paintYellow.setFlags(1);
        this.paintGreen = new Paint();
        this.paintGreen.setStrokeWidth(6.0f);
        this.paintGreen.setStyle(Style.FILL);
        this.paintGreen.setColor(Color.parseColor("#00c853"));
        this.paintGreen.setFlags(1);
        this.paintBlue = new Paint();
        this.paintBlue.setStrokeWidth(6.0f);
        this.paintBlue.setStyle(Style.FILL);
        this.paintBlue.setColor(Color.parseColor("#00a9ff"));
        this.paintBlue.setFlags(1);
        this.paintRed = new Paint();
        this.paintRed.setStrokeWidth(6.0f);
        this.paintRed.setStyle(Style.FILL);
        this.paintRed.setColor(Color.parseColor("#ff5722"));
        this.paintRed.setFlags(1);
        this.colorList.add(this.paintYellow);
        this.colorList.add(this.paintGreen);
        this.colorList.add(this.paintRed);
        this.colorList.add(this.paintBlue);
        this.paintYellowBlink = new Paint();
        this.paintYellowBlink.setStrokeWidth(6.0f);
        this.paintYellowBlink.setStyle(Style.FILL);
        this.paintYellowBlink.setColor(Color.parseColor("#FFD600"));
        this.paintYellowBlink.setFlags(1);
        this.paintGreenBlink = new Paint();
        this.paintGreenBlink.setStrokeWidth(6.0f);
        this.paintGreenBlink.setStyle(Style.FILL);
        this.paintGreenBlink.setColor(Color.parseColor("#689F38"));
        this.paintGreenBlink.setFlags(1);
        this.paintBlueBlink = new Paint();
        this.paintBlueBlink.setStrokeWidth(6.0f);
        this.paintBlueBlink.setStyle(Style.FILL);
        this.paintBlueBlink.setColor(Color.parseColor("#0091EA"));
        this.paintBlueBlink.setFlags(1);
        this.paintRedBlink = new Paint();
        this.paintRedBlink.setStrokeWidth(6.0f);
        this.paintRedBlink.setStyle(Style.FILL);
        this.paintRedBlink.setColor(Color.parseColor("#D50000"));
        this.paintRedBlink.setFlags(1);
        this.colorListBlink.add(this.paintYellowBlink);
        this.colorListBlink.add(this.paintGreenBlink);
        this.colorListBlink.add(this.paintRedBlink);
        this.colorListBlink.add(this.paintBlueBlink);
        this.rockPaintListFill[0] = this.paintYellow;
        this.rockPaintListFill[1] = this.paintGreen;
        this.rockPaintListFill[2] = this.paintBlue;
        this.rockPaintListFill[3] = this.paintRed;
        this.outLinesPaintYellow = new Paint();
        this.outLinesPaintYellow.setStrokeWidth(6.0f);
        this.outLinesPaintYellow.setStyle(Style.STROKE);
        this.outLinesPaintYellow.setColor(InputDeviceCompat.SOURCE_ANY);
        this.outLinesPaintYellow.setFlags(1);
        this.outLinesPaintGreen = new Paint();
        this.outLinesPaintGreen.setStrokeWidth(6.0f);
        this.outLinesPaintGreen.setStyle(Style.STROKE);
        this.outLinesPaintGreen.setColor(-16711936);
        this.outLinesPaintGreen.setFlags(1);
        this.outLinesPaintBlue = new Paint();
        this.outLinesPaintBlue.setStrokeWidth(6.0f);
        this.outLinesPaintBlue.setStyle(Style.STROKE);
        this.outLinesPaintBlue.setColor(-16776961);
        this.outLinesPaintBlue.setFlags(1);
        this.outLinesPaintRed = new Paint();
        this.outLinesPaintRed.setStrokeWidth(6.0f);
        this.outLinesPaintRed.setStyle(Style.STROKE);
        this.outLinesPaintRed.setColor(SupportMenu.CATEGORY_MASK);
        this.outLinesPaintRed.setFlags(1);
        this.rockPaintListStroke[0] = this.outLinesPaintYellow;
        this.rockPaintListStroke[1] = this.outLinesPaintGreen;
        this.rockPaintListStroke[2] = this.outLinesPaintBlue;
        this.rockPaintListStroke[3] = this.outLinesPaintRed;
        this.diceBoxPaint = new Paint();
        this.diceBoxPaint.setColor(Color.parseColor("#e5d9d7"));
        this.diceBoxPaint.setStyle(Style.FILL);
        this.diceBoxPaint.setTextSize(24.0f);
        this.diceBoxPaint.setFlags(1);
        this.playerPositionPaint = new Paint();
        this.playerPositionPaint.setStrokeWidth(4.0f);
        this.playerPositionPaint.setColor(InputDeviceCompat.SOURCE_ANY);
        this.playerPositionPaint.setStyle(Style.FILL_AND_STROKE);
        this.playerPositionPaint.setTextSize(24.0f);
        this.playerPositionPaint.setFlags(1);
        this.compPositionPaint = new Paint();
        this.compPositionPaint.setStrokeWidth(4.0f);
        this.compPositionPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.compPositionPaint.setStyle(Style.FILL_AND_STROKE);
        this.compPositionPaint.setTextSize(24.0f);
        this.compPositionPaint.setFlags(1);
        this.textColorPaint = new Paint();
        this.textColorPaint.setStrokeWidth(1.0f);
        this.textColorPaint.setColor(Color.parseColor("#424242"));
        this.textColorPaint.setStyle(Style.STROKE);
        this.textColorPaint.setTextSize(8.0f);
        this.textColorPaint.setFlags(1);
        this.textColorPaint2 = new Paint();
        this.textColorPaint2.setStrokeWidth(3.0f);
        this.textColorPaint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.textColorPaint2.setStyle(Style.STROKE);
        this.textColorPaint2.setTextSize(20.0f);
        this.textColorPaint2.setFlags(1);
        this.rankingPaint = new Paint();
        this.rankingPaint.setStrokeWidth(this.rankNoStrokeWidth);
        this.rankingPaint.setColor(Color.parseColor("#3E2723"));
        this.rankingPaint.setStyle(Style.STROKE);
        this.rankingPaint.setTextSize((float) this.rankNoSize);
        this.rankingPaint.setFlags(1);
        this.gameOverPaint = new Paint();
        this.gameOverPaint.setStrokeWidth(this.gameOverStrokeWidth);
        this.gameOverPaint.setColor(Color.parseColor("#3E2723"));
        this.gameOverPaint.setStyle(Style.STROKE);
        this.gameOverPaint.setTextSize((float) this.gameOverSize);
        this.gameOverPaint.setFlags(1);
        this.winsPaintFill = new Paint();
        this.winsPaintFill.setStrokeWidth(6.0f);
        this.winsPaintFill.setColor(InputDeviceCompat.SOURCE_ANY);
        this.winsPaintFill.setStyle(Style.FILL);
        this.winsPaintFill.setFlags(1);
        this.winsPaintStrok = new Paint();
        this.winsPaintStrok.setStrokeWidth(6.0f);
        this.winsPaintStrok.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.winsPaintStrok.setStyle(Style.STROKE);
        this.winsPaintStrok.setFlags(1);
    }

    protected void onDraw(Canvas canvas) {
        int row;
        super.onDraw(canvas);
        drawBgdBoxes(canvas);
        int i = 0;
        while (i < 4) {
            int[] box = this.homeBoxsOuter[i];
            GridBox gridBox0 = (GridBox) this.boxHashMap.get(Integer.valueOf(box[0]));
            GridBox gridBox1 = (GridBox) this.boxHashMap.get(Integer.valueOf(box[1]));
            Paint color = (Paint) this.colorList.get(i);
            if (!this.gameComplete.booleanValue() && this.playerNow.getPlayerId() == i) {
                color = this.blinkMarker == 0 ? (Paint) this.colorList.get(i) : (Paint) this.colorListBlink.get(i);
            }
            canvas.drawRect(gridBox0.getX() * this.gridBoxDpWidth, gridBox0.getY() * this.gridBoxDpHeight, (gridBox1.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (gridBox1.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, color);
            canvas.drawRect(gridBox0.getX() * this.gridBoxDpWidth, gridBox0.getY() * this.gridBoxDpHeight, (gridBox1.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (gridBox1.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, this.gridLinesPaint);
            i++;
        }
        for (i = 0; i < 4; i++) {
            int[] box = this.homeBoxsInner[i];
            GridBox  gridBox0 = (GridBox) this.boxHashMap.get(Integer.valueOf(box[0]));
            GridBox   gridBox1 = (GridBox) this.boxHashMap.get(Integer.valueOf(box[1]));
            canvas.drawRect(gridBox0.getX() * this.gridBoxDpWidth, gridBox0.getY() * this.gridBoxDpHeight, (gridBox1.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (gridBox1.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, this.paintWhite);
            canvas.drawRect(gridBox0.getX() * this.gridBoxDpWidth, gridBox0.getY() * this.gridBoxDpHeight, (gridBox1.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (gridBox1.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, this.gridLinesPaint);
        }
        for (i = 0; i < 4; i++) {
            int j;
            for (j = 0; j < 4; j++) {
                GridBox gridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.homeCircles[i][j]));
                canvas.drawCircle(gridBox.getX() * this.gridBoxDpWidth, gridBox.getY() * this.gridBoxDpHeight, this.gridBoxDpWidth / 2.0f, (Paint) this.colorList.get(i));
                canvas.drawCircle(gridBox.getX() * this.gridBoxDpWidth, gridBox.getY() * this.gridBoxDpHeight, this.gridBoxDpWidth / 2.0f, this.gridLinesPaint);
            }
        }
        for (i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                GridBox  gridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.colorPaths[i][j]));
                canvas.drawRect(gridBox.getX() * this.gridBoxDpWidth, gridBox.getY() * this.gridBoxDpHeight, (gridBox.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (gridBox.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, (Paint) this.colorList.get(i));
                canvas.drawRect(gridBox.getX() * this.gridBoxDpWidth, gridBox.getY() * this.gridBoxDpHeight, (gridBox.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (gridBox.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, this.gridLinesPaint);
            }
        }
        for (i = 0; i < 4; i++) {
            canvas.drawPath(this.trianglePathList[i], (Paint) this.colorList.get(i));
            canvas.drawPath(this.trianglePathList[i], this.gridLinesPaint);
        }
        for (row = 0; row < 4; row++) {
            if (this.playerNow.getPlayerId() == row) {
                if (this.blink == 0) {
                    canvas.drawRoundRect(this.diceBoxRectFs[row], 6.0f, 6.0f, this.diceBoxPaint);
                    this.blink = 1;
                } else {
                    canvas.drawRoundRect(this.diceBoxRectFs[row], 6.0f, 6.0f, this.paintWhite);
                    this.blink = 0;
                }
                canvas.drawRoundRect(this.diceBoxRectFs[row], 6.0f, 6.0f, this.gridLinesPaint);
            } else {
                canvas.drawRoundRect(this.diceBoxRectFs[row], 6.0f, 6.0f, this.diceBoxPaint);
                canvas.drawRoundRect(this.diceBoxRectFs[row], 6.0f, 6.0f, this.gridLinesPaint);
            }
            if (this.playerNow.getPlayerId() == row) {
                ((Drawable) ((HashMap) this.diceSidesMapList.get(row)).get(this.diceValue)).draw(canvas);
            }
        }
        for (row = 0; row < 4; row++) {
            this.markerAdjecentDiceBox[row].draw(canvas);
        }
        for (row = 0; row < 4; row++) {
            this.safeStars[row].draw(canvas);
        }
        this.youImage.draw(canvas);
        this.comp1.draw(canvas);
        this.comp2.draw(canvas);
        this.comp3.draw(canvas);
        for (Drawable token : this.markersInGameList) {
            token.draw(canvas);
        }
        Iterator it = this.activeMarkersList.iterator();
        while (it.hasNext()) {
            DrawableMarker activeMarker = (DrawableMarker) it.next();
            if (this.blinkMarker == 0) {
                activeMarker.getDrawableMarker().setColorFilter(Color.parseColor("#424242"), Mode.MULTIPLY);
            } else {
                activeMarker.getDrawableMarker().clearColorFilter();
            }
        }
        if (this.blinkMarker == 0) {
            this.blinkMarker++;
        } else {
            this.blinkMarker = (this.blinkMarker + 1) % 2;
        }
        if (!this.playerRankList.isEmpty()) {
            for (i = 0; i < this.playerRankList.size(); i++) {
                int[] boxInner = this.homeBoxsInner[((LudoPlayer) this.playerRankList.get(i)).getPlayerId()];
                GridBox gridBox0 = (GridBox) this.boxHashMap.get(Integer.valueOf(boxInner[0]));
                GridBox gridBox1 = (GridBox) this.boxHashMap.get(Integer.valueOf(boxInner[1]));
                canvas.drawText(new StringBuilder(String.valueOf(i + 1)).toString(), (((gridBox0.getX() * this.gridBoxDpWidth) + (gridBox1.getX() * this.gridBoxDpWidth)) + (this.gridBoxDpWidth / 2.0f)) / 2.0f, (((gridBox0.getY() * this.gridBoxDpHeight) + (gridBox1.getY() * this.gridBoxDpHeight)) + ((this.gridBoxDpHeight * 3.0f) / 2.0f)) / 2.0f, this.rankingPaint);
            }
        }
        if (this.gameComplete.booleanValue()) {
            GridBox gridBox0 = (GridBox) this.boxHashMap.get(Integer.valueOf(this.gameOverRectangle[0]));
            GridBox  gridBox1 = (GridBox) this.boxHashMap.get(Integer.valueOf(this.gameOverRectangle[1]));
            canvas.drawRect(gridBox0.getX() * this.gridBoxDpWidth, gridBox0.getY() * this.gridBoxDpHeight, (gridBox1.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (gridBox1.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, this.winsPaintFill);
            canvas.drawRect(gridBox0.getX() * this.gridBoxDpWidth, gridBox0.getY() * this.gridBoxDpHeight, (gridBox1.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (gridBox1.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, this.gameOverPaint);
            canvas.drawText("Game Over", ((((gridBox0.getX() * this.gridBoxDpWidth) + (gridBox1.getX() * this.gridBoxDpWidth)) + this.gridBoxDpWidth) * 2.0f) / 5.0f, (((gridBox0.getY() * this.gridBoxDpHeight) + (gridBox1.getY() * this.gridBoxDpHeight)) + this.gridBoxDpHeight) / 2.0f, this.gameOverPaint);
            canvas.drawText("Click to restart.", ((((gridBox0.getX() * this.gridBoxDpWidth) + (gridBox1.getX() * this.gridBoxDpWidth)) + this.gridBoxDpWidth) * 2.0f) / 5.0f, (((gridBox0.getY() * this.gridBoxDpHeight) + (gridBox1.getY() * this.gridBoxDpHeight)) + (2.0f * this.gridBoxDpHeight)) / 2.0f, this.gameOverPaint);
        }
    }

    private void drawBgdBoxes(Canvas canvas) {
        for (Integer i = Integer.valueOf(0); i.intValue() < this.boxesToVisitArray.size(); i = Integer.valueOf(i.intValue() + 1)) {
            GridBox box = (GridBox) this.boxesToVisitArray.get(i.intValue());
            Paint bgdPaint = this.paintWhite;
            canvas.drawRect(box.getX() * this.gridBoxDpWidth, box.getY() * this.gridBoxDpHeight, (box.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (box.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, bgdPaint);
            canvas.drawRect(box.getX() * this.gridBoxDpWidth, box.getY() * this.gridBoxDpHeight, (box.getX() * this.gridBoxDpWidth) + this.gridBoxDpWidth, (box.getY() * this.gridBoxDpHeight) + this.gridBoxDpHeight, this.gridLinesPaint);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (this.gameComplete.booleanValue()) {
            this.gameComplete = Boolean.valueOf(false);
            this.diceReadyForClick = Boolean.valueOf(true);
            this.markerReadyForClick = Boolean.valueOf(false);
            this.playerRankList.clear();
            this.activeMarkersList.clear();
            this.markersInGame = (Drawable[][]) Array.newInstance(Drawable.class, new int[]{4, 4});
            this.diceValue = Integer.valueOf(1);
            this.playerList = new ArrayList();
            this.playerList.add(new LudoPlayer(0, "", true, ""));
            this.playerList.add(new LudoPlayer(1, "", true, ""));
            this.playerList.add(new LudoPlayer(2, "", true, ""));
            this.playerList.add(new LudoPlayer(3, "", false, "BLUE"));
            this.playerNow = (LudoPlayer) this.playerList.get(3);
            this.takenBoxMap = new HashMap();
            initializeMarkersInGame();
            for (int i = 1; i < 4; i += 2) {
                this.playerOutOfGame[i] = Integer.valueOf(0);
            }
            this.gameState = Integer.valueOf(0);
        }
        if (this.markerReadyForClick.booleanValue()) {
            Iterator it = this.activeMarkersList.iterator();
            while (it.hasNext()) {
                DrawableMarker drawableMarker = (DrawableMarker) it.next();
                if (drawableMarker.getDrawableMarker().getBounds().contains((int) x, (int) y)) {
                    this.playerRockToMove = drawableMarker;
                    it = this.activeMarkersList.iterator();
                    while (it.hasNext()) {
                        ((DrawableMarker) it.next()).getDrawableMarker().clearColorFilter();
                    }
                    this.activeMarkersList.clear();
                    play();
                }
            }
        }
        if (this.diceReadyForClick.booleanValue() && this.diceBoxRectFs[this.playerNow.getPlayerId()].contains(x, y)) {
            switch (event.getAction()) {
                case 1:
                    synchronized (this.diceReadyForClick) {
                        this.diceReadyForClick = Boolean.valueOf(false);
                    }
                    play();
                    break;
            }
        }
        return true;
    }

    private void gameLoop() {
        Collections.sort(this.markersInGameList, new C03931());
        this.gameLoopHandler.sleep(this.gameLoopDelay);
    }

    private void play() {
        if (LudoVsCompTwoplayerActivity.play.booleanValue()) {
            Iterator it;
            switch (this.gameState.intValue()) {
                case 0:
                    this.maxDiceRolls = Integer.valueOf(new Random().nextInt(4) + 3);
                    this.gameState = Integer.valueOf(1);
                    this.f55l = Integer.valueOf(0);
                    this.delayHandler.sleep(this.mDiceDelay);
                    return;
                case 1:
                    if (this.f55l.intValue() < this.maxDiceRolls.intValue()) {
                        this.f55l = Integer.valueOf(this.f55l.intValue() + 1);
                        Random randomValue = new Random();
                        Integer diceValueNew = Integer.valueOf(randomValue.nextInt(7) + 1);
                        if (diceValueNew.intValue() > 6) {
                            diceValueNew = Integer.valueOf(6);
                        }
                        while (diceValueNew == this.diceValue) {
                            diceValueNew = Integer.valueOf(randomValue.nextInt(7) + 1);
                            if (diceValueNew.intValue() > 6) {
                                diceValueNew = Integer.valueOf(6);
                            }
                        }
                        this.diceValue = diceValueNew;
                        StaticVariables.soundUtil.playSound(1, 1);
                        this.delayHandler.sleep(this.mDiceRollDelay);
                        return;
                    }
                    this.activeMarkersList = new ArrayList();
                    int col = 0;
                    while (col < 4) {
                        if (!this.destHomeList.contains(Integer.valueOf(this.playerNow.getPlayerRockAry()[col])) && (this.playerNow.getPlayerRockAry()[col] != -1 || this.diceValue.intValue() == 6)) {
                            if (this.playerNow.getPlayerRockAry()[col] == -1 && this.diceValue.intValue() == 6) {
                                this.activeMarkersList.add(new DrawableMarker(this.markersInGame[this.playerNow.getPlayerId()][col], col));
                            } else {
                                if (this.playerNow.getPlayerRockAry()[col] + this.diceValue.intValue() <= this.playerPath[0].length - 1 && this.playerNow.getPlayerRockAry()[col] > -1) {
                                    this.activeMarkersList.add(new DrawableMarker(this.markersInGame[this.playerNow.getPlayerId()][col], col));
                                }
                            }
                        }
                        col++;
                    }
                    if (!this.activeMarkersList.isEmpty()) {
                        if (this.playerNow.getPlayerId() != this.youPlayerId.intValue()) {
                            this.markerReadyForClick = Boolean.valueOf(false);
                        } else {
                            this.markerReadyForClick = Boolean.valueOf(true);
                        }
                        this.boxesMarkerWillMove = this.diceValue.intValue();
                        if (this.diceValue.intValue() == 6) {
                            this.diceValuePreviousWasSix = Boolean.valueOf(true);
                        } else {
                            this.diceValuePreviousWasSix = Boolean.valueOf(false);
                        }
                        this.gameState = Integer.valueOf(2);
                        this.delayHandler.sleep(this.mDiceDelay);
                        return;
                    } else if (this.diceValue.intValue() == 6) {
                        this.gameState = Integer.valueOf(0);
                        if (this.playerRankList.contains(this.playerNow)) {
                            do {
                                this.playerNow = (LudoPlayer) this.playerList.get((this.playerNow.getPlayerId() + 1) % 4);
                            } while (this.playerOutOfGame[this.playerNow.getPlayerId()].intValue() > 0);
                        }
                        this.diceReadyForClick = Boolean.valueOf(true);
                        this.markerReadyForClick = Boolean.valueOf(false);
                        this.playerRockToMove = null;
                        compRollDiceAuto();
                        return;
                    } else {
                        do {
                            this.playerNow = (LudoPlayer) this.playerList.get((this.playerNow.getPlayerId() + 1) % 4);
                        } while (this.playerOutOfGame[this.playerNow.getPlayerId()].intValue() > 0);
                        this.diceValue = Integer.valueOf(1);
                        this.diceReadyForClick = Boolean.valueOf(true);
                        this.playerRockToMove = null;
                        this.gameState = Integer.valueOf(0);
                        compRollDiceAuto();
                        return;
                    }
                case 2:
                    if (this.playerNow.getPlayerId() != this.youPlayerId.intValue() || this.activeMarkersList.size() == 1) {
                        Integer activeMarkerCount = Integer.valueOf(this.activeMarkersList.size());
                        Integer markerToBeMoved = Integer.valueOf(0);
                        if (activeMarkerCount.intValue() > 1) {
                            markerToBeMoved = Integer.valueOf(new Random().nextInt(activeMarkerCount.intValue()));
                        } else {
                            markerToBeMoved = Integer.valueOf(0);
                        }
                        this.playerRockToMove = (DrawableMarker) this.activeMarkersList.get(markerToBeMoved.intValue());
                        this.markerReadyForClick = Boolean.valueOf(false);
                        it = this.activeMarkersList.iterator();
                        while (it.hasNext()) {
                            ((DrawableMarker) it.next()).getDrawableMarker().clearColorFilter();
                        }
                        this.activeMarkersList.clear();
                        this.gameState = Integer.valueOf(3);
                        this.delayHandler.sleep(this.markerClickDelay);
                    }
                    if (this.playerRockToMove != null) {
                        this.markerReadyForClick = Boolean.valueOf(false);
                        this.gameState = Integer.valueOf(3);
                        this.delayHandler.sleep(this.markerClickDelay);
                        return;
                    }
                    return;
                case 3:
                    if (this.boxesMarkerWillMove > 0) {
                        ArrayList<TakenBox> takenBoxList;
                        GridBox markerGridBox;
                        float offset;
                        TakenBox takenBox1;
                        if (this.boxesMarkerWillMove == 6 && this.playerNow.getPlayerRockAry()[this.playerRockToMove.getColMarker()] == -1) {
                            Integer gridBoxNumber = Integer.valueOf(this.playerPath[this.playerNow.getPlayerId()][START_PATH.intValue()]);
                            if (this.takenBoxMap.containsKey(gridBoxNumber)) {
                                takenBoxList = (ArrayList) this.takenBoxMap.get(gridBoxNumber);
                            } else {
                                takenBoxList = new ArrayList();
                            }
                            takenBoxList.add(new TakenBox(Integer.valueOf(this.playerNow.getPlayerId()), Integer.valueOf(this.playerRockToMove.getColMarker())));
                            this.playerNow.getPlayerRockAry()[this.playerRockToMove.getColMarker()] = 0;
                            this.takenBoxMap.put(gridBoxNumber, takenBoxList);
                            this.boxesMarkerWillMove = 0;
                            markerGridBox = (GridBox) this.boxHashMap.get(gridBoxNumber);
                            if (takenBoxList.size() > 1) {
                                offset = 0.0f;
                                it = takenBoxList.iterator();
                                while (it.hasNext()) {
                                    takenBox1 = (TakenBox) it.next();
                                    offset += this.gridBoxDpWidth / 10.0f;
                                    this.markersInGame[takenBox1.getPlayerId().intValue()][takenBox1.getMarkerNumber().intValue()].setBounds((int) ((((markerGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)) + offset), (int) (((markerGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpHeight * 3.0f) / 4.0f)) + offset), (int) ((((markerGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)) + offset), (int) (((markerGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)) + offset));
                                }
                            } else {
                                this.markersInGame[this.playerNow.getPlayerId()][this.playerRockToMove.getColMarker()].setBounds((int) (((markerGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)), (int) ((markerGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpHeight * 3.0f) / 4.0f)), (int) (((markerGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)), (int) ((markerGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)));
                            }
                        } else if (this.playerNow.getPlayerRockAry()[this.playerRockToMove.getColMarker()] != -1 || this.boxesMarkerWillMove == 6) {
                            int i;
                            int[] playerRockAry = this.playerNow.getPlayerRockAry();
                            int colMarker = this.playerRockToMove.getColMarker();
                            playerRockAry[colMarker] = playerRockAry[colMarker] + 1;
                            Integer markerBeingMoved = Integer.valueOf(this.playerNow.getPlayerRockAry()[this.playerRockToMove.getColMarker()]);
                            if (this.takenBoxMap.containsKey(Integer.valueOf(this.playerPath[this.playerNow.getPlayerId()][markerBeingMoved.intValue() - 1]))) {
                                takenBoxList = (ArrayList) this.takenBoxMap.get(Integer.valueOf(this.playerPath[this.playerNow.getPlayerId()][markerBeingMoved.intValue() - 1]));
                                i = 0;
                                while (i < takenBoxList.size()) {
                                    if (((TakenBox) takenBoxList.get(i)).getPlayerId().intValue() == this.playerNow.getPlayerId() && ((TakenBox) takenBoxList.get(i)).getMarkerNumber().intValue() == this.playerRockToMove.getColMarker()) {
                                        takenBoxList.remove(i);
                                    } else {
                                        i++;
                                    }
                                }
                            }
                            if (this.boxesMarkerWillMove == 1) {
                                ArrayList<TakenBox> takenBoxListInner;
                                Integer currentPlaceOfMarker = Integer.valueOf(this.playerPath[this.playerNow.getPlayerId()][markerBeingMoved.intValue()]);
                                if (this.takenBoxMap.containsKey(currentPlaceOfMarker)) {
                                    takenBoxListInner = (ArrayList) this.takenBoxMap.get(Integer.valueOf(this.playerPath[this.playerNow.getPlayerId()][markerBeingMoved.intValue()]));
                                    if (!this.safeBoxesMap.containsKey(currentPlaceOfMarker)) {
                                        i = 0;
                                        while (i < takenBoxListInner.size()) {
                                            LudoPlayer playerAlreadyAtBox = (LudoPlayer) this.playerList.get(((TakenBox) takenBoxListInner.get(i)).getPlayerId().intValue());
                                            if (this.playerNow.getPlayerId() != ((TakenBox) takenBoxListInner.get(i)).getPlayerId().intValue()) {
                                                playerAlreadyAtBox.getPlayerRockAry()[((TakenBox) takenBoxListInner.get(i)).getMarkerNumber().intValue()] = -1;
                                                GridBox homeGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.homeCircles[playerAlreadyAtBox.getPlayerId()][((TakenBox) takenBoxListInner.get(i)).getMarkerNumber().intValue()]));
                                                this.markersInGame[playerAlreadyAtBox.getPlayerId()][((TakenBox) takenBoxListInner.get(i)).getMarkerNumber().intValue()].setBounds((int) ((homeGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((homeGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)), (int) ((homeGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((homeGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)) - (this.gridBoxDpWidth / 2.0f)));
                                                takenBoxListInner.remove(i);
                                                StaticVariables.soundUtil.playSound(4, 1);
                                                this.landedOnOpponentsToken = Boolean.valueOf(true);
                                            } else {
                                                i++;
                                            }
                                        }
                                    }
                                } else {
                                    takenBoxListInner = new ArrayList();
                                }
                                takenBoxListInner.add(new TakenBox(Integer.valueOf(this.playerNow.getPlayerId()), Integer.valueOf(this.playerRockToMove.getColMarker())));
                                this.takenBoxMap.put(Integer.valueOf(this.playerPath[this.playerNow.getPlayerId()][markerBeingMoved.intValue()]), takenBoxListInner);
                                markerGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.playerPath[this.playerNow.getPlayerId()][this.playerNow.getPlayerRockAry()[this.playerRockToMove.getColMarker()]]));
                                if (takenBoxListInner.size() > 1) {
                                    offset = 0.0f;
                                    it = takenBoxListInner.iterator();
                                    while (it.hasNext()) {
                                        takenBox1 = (TakenBox) it.next();
                                        offset += this.gridBoxDpWidth / 10.0f;
                                        this.markersInGame[takenBox1.getPlayerId().intValue()][takenBox1.getMarkerNumber().intValue()].setBounds((int) ((((markerGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)) + offset), (int) (((markerGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpHeight * 3.0f) / 4.0f)) + offset), (int) ((((markerGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)) + offset), (int) (((markerGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)) + offset));
                                    }
                                } else {
                                    this.markersInGame[this.playerNow.getPlayerId()][this.playerRockToMove.getColMarker()].setBounds((int) (((markerGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)), (int) ((markerGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((markerGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)), (int) ((markerGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)));
                                }
                            } else {
                                markerGridBox = (GridBox) this.boxHashMap.get(Integer.valueOf(this.playerPath[this.playerNow.getPlayerId()][this.playerNow.getPlayerRockAry()[this.playerRockToMove.getColMarker()]]));
                                this.markersInGame[this.playerNow.getPlayerId()][this.playerRockToMove.getColMarker()].setBounds((int) (((markerGridBox.getX() * this.gridBoxDpWidth) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)), (int) ((markerGridBox.getY() * this.gridBoxDpHeight) - ((this.gridBoxDpWidth * 3.0f) / 4.0f)), (int) (((markerGridBox.getX() * this.gridBoxDpWidth) + ((this.gridBoxDpWidth * 3.0f) / 4.0f)) + (this.gridBoxDpWidth / 2.0f)), (int) ((markerGridBox.getY() * this.gridBoxDpHeight) + ((this.gridBoxDpHeight * 3.0f) / 4.0f)));
                            }
                            this.boxesMarkerWillMove--;
                        } else {
                            this.boxesMarkerWillMove = 0;
                        }
                        if (this.playerNow.getPlayerRockAry()[this.playerRockToMove.getColMarker()] == this.playerPath[0].length - 1) {
                            this.reachedHome = Boolean.valueOf(true);
                            StaticVariables.soundUtil.playSound(5, 1);
                            this.delayHandler.sleep(this.mDiceDelay);
                            return;
                        }
                        StaticVariables.soundUtil.playSound(3, 1);
                        this.delayHandler.sleep(this.mDiceDelay);
                        return;
                    }
                    StaticVariables.soundUtil.playSound(2, 1);
                    if (this.playerNow.getPlayerRockAry()[0] == this.playerPath[0].length - 1 && this.playerNow.getPlayerRockAry()[1] == this.playerPath[0].length - 1 && this.playerNow.getPlayerRockAry()[2] == this.playerPath[0].length - 1 && this.playerNow.getPlayerRockAry()[3] == this.playerPath[0].length - 1) {
                        this.playerRankList.add(this.playerNow);
                        this.playerOutOfGame[this.playerNow.getPlayerId()] = Integer.valueOf(1);
                        if (this.playerRankList.size() == 1) {
                            this.gameComplete = Boolean.valueOf(true);
                        }
                    }
                    if (this.diceValuePreviousWasSix.booleanValue() || this.landedOnOpponentsToken.booleanValue() || this.reachedHome.booleanValue()) {
                        this.gameState = Integer.valueOf(0);
                        if (this.playerRankList.contains(this.playerNow)) {
                            do {
                                this.playerNow = (LudoPlayer) this.playerList.get((this.playerNow.getPlayerId() + 1) % 4);
                            } while (this.playerOutOfGame[this.playerNow.getPlayerId()].intValue() > 0);
                        }
                        this.diceReadyForClick = Boolean.valueOf(true);
                        this.markerReadyForClick = Boolean.valueOf(false);
                        this.playerRockToMove = null;
                        this.landedOnOpponentsToken = Boolean.valueOf(false);
                        this.reachedHome = Boolean.valueOf(false);
                        compRollDiceAuto();
                        return;
                    }
                    this.gameState = Integer.valueOf(0);
                    do {
                        this.playerNow = (LudoPlayer) this.playerList.get((this.playerNow.getPlayerId() + 1) % 4);
                    } while (this.playerOutOfGame[this.playerNow.getPlayerId()].intValue() > 0);
                    this.diceValue = Integer.valueOf(1);
                    this.diceReadyForClick = Boolean.valueOf(true);
                    this.playerRockToMove = null;
                    compRollDiceAuto();
                    return;
                default:
                    return;
            }
        }
    }

    private void compRollDiceAuto() {
        if (this.playerNow.getPlayerId() != this.youPlayerId.intValue() && !this.gameComplete.booleanValue()) {
            this.delayHandler.sleep(this.longDiceDelay);
            this.diceReadyForClick = Boolean.valueOf(false);
        }
    }
}
