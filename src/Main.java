package src;
import src.Buttons;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
///
 /*********************************************************
 *  Author: Yuyaoo C.
 *  Development period: April 2018 - May 2018.
 *  Project Description:
 *  This game, One Stroke, is simple, yet intriguing puzzle game.
 *  I really enjoyed playing this game a long time ago, so
 *  I decided to write my own version of the game.
 *
 *  Features:
 *  There are currently 10 levels with hints.
 *  The player can restart the game, or return to home page.
 *  For each level player plays,
 *  they unlock 3 other levels. There are simple tutorials
 *  to get the player started with the rules of the game.
 *  The player can also design and play their own levels.
 *
 *  Development goals:
 *  Add more levels
 *  Support more powerful level design.
 *  Maybe add more cats and tomatoes.
 *********************************************************/

public class Main extends JFrame{

   /* 
      1 - instructions page
      2 - home page
      3 - levels page
      4 - game page
      5 - create puzzle page
   */
   
   /*** Variable Dictionary ******************************************************
   * xx and yy - Store the x and y coordinates of the mouse when it is clicked   *
   * pageNum - Indicates the screen(page) the player is on. ex. home screen = 1  *
   * numVertex - Stores the number of vertices of each puzzle                *
     numPaths - the number of pathways of each puzzle
   * orders - Indicates the order of vertices that the player has clicked    *
     levels - stores the currently unlocked levels    * 
     hints - stores the suggested starting positions of each level
     matrixVertices - the matrix that indicates patterns of paths for each level
     select - an array of level buttons
   ******************************************************/

   static int xx = -1;
   static int yy;
   static int pageNum = 2;
   static int [] numVertex = {5,5,7,7,10,11,11,11,11,13,0};
   static int [] numPaths = {5,6,10,9,12,14,18,13,13,15,-1};
   static ArrayList<Integer> orders = new ArrayList<Integer>();  
   static ArrayList<Integer> levels = new ArrayList<Integer>();
   static int level =0;
   static boolean test=false;
   static int[][]matrix;
   static int[][]tempMatrix=new int [100][100];
   static int previousPage = -1;
   static boolean click;
   static boolean clickk;
   static int t=-1;
   static int s=-1;
   static boolean finished = false;
   static boolean resulted = false;
   static int numLevels = 10;
   static int[]hints = {4,1,2,1,1,3,10,5,6,2};
   static boolean paused = false;
   static int[][][]matrixVertices ={{{0,1,0,0,1},                                     
                                     {1,0,1,0,0},
                                     {0,1,0,1,0},
                                     {0,0,1,0,1},
                                     {1,0,0,1,0}},  //Level 1
                                     
                                    {{0,1,1,0,0},
                                     {1,0,1,0,1},
                                     {1,1,0,1,0},                                                                                                                                                                                                                                                                                                                                                                                    
                                     {0,0,1,0,1},
                                     {0,1,0,1,0}},    //Level 2
                                     
                                    {{0,1,0,1,0,1,1},
                                     {1,0,1,0,0,0,0},
                                     {0,1,0,1,1,0,0},
                                     {1,0,1,0,0,1,0},
                                     {0,0,1,0,0,1,0},
                                     {1,0,0,1,1,0,1},
                                     {1,0,0,0,0,1,0}},  //Level 3
                                     
                                    {{0,1,0,0,0,1,0},
                                     {1,0,1,1,0,0,0},
                                     {0,1,0,0,1,0,0},
                                     {0,1,0,0,1,0,0},
                                     {0,0,1,1,0,1,1},
                                     {1,0,0,0,1,0,1},
                                     {0,0,0,0,1,1,0}},    //Level 4
                                     
                                    {{0,1,0,0,0,0,0,0,0,1},
                                     {1,0,1,0,0,0,0,0,0,0},
                                     {0,1,0,0,0,0,1,0,0,0},
                                     {0,0,0,0,0,0,1,0,0,1},
                                     {0,0,0,0,0,0,1,0,0,1},
                                     {0,0,0,0,0,0,0,1,1,0},
                                     {0,0,1,1,1,0,0,1,0,0},
                                     {0,0,0,0,0,1,1,0,0,0},
                                     {0,0,0,0,0,1,0,0,0,1},
                                     {1,0,0,1,1,0,0,0,1,0}}, //Level 5
                                     
                                    {{0,1,0,0,0,0,0,0,1,0,0},
                                     {1,0,1,0,0,0,0,1,0,0,0},
                                     {0,1,0,1,0,0,0,0,0,0,0},
                                     {0,0,1,0,1,0,0,0,0,0,1},
                                     {0,0,0,1,0,1,1,0,0,1,0},
                                     {0,0,0,0,1,0,0,1,0,0,0},
                                     {0,0,0,0,1,0,0,1,0,0,0},
                                     {0,1,0,0,0,1,1,0,1,0,0},
                                     {1,0,0,0,0,0,0,1,0,0,0},
                                     {0,0,0,0,1,0,0,0,0,0,1},
                                     {0,0,0,1,0,0,0,0,0,1,0}},  //Level 6
                                     
                                    {{0,1,0,0,0,0,0,0,0,0,1},
                                     {1,0,1,0,0,0,0,0,0,1,0},
                                     {0,1,0,1,0,0,0,0,1,0,1},
                                     {0,0,1,0,1,0,0,1,0,1,0},
                                     {0,0,0,1,0,1,1,0,1,0,0},
                                     {0,0,0,0,1,0,0,1,0,0,0},
                                     {0,0,0,0,1,0,0,1,0,0,0},
                                     {0,0,0,1,0,1,1,0,1,0,0},
                                     {0,0,1,0,1,0,0,1,0,1,0},
                                     {0,1,0,1,0,0,0,0,1,0,1},
                                     {1,0,1,0,0,0,0,0,0,1,0}}, //Level 7
                                     
                                    {{0,0,1,0,1,0,0,0,0,0,0},
                                     {0,0,1,1,0,0,0,0,0,0,0},
                                     {1,1,0,0,0,1,1,0,0,0,0},
                                     {0,1,0,0,0,0,0,0,1,0,0},
                                     {1,0,0,0,0,0,0,0,1,0,0},
                                     {0,0,1,0,0,0,1,0,0,0,1},
                                     {0,0,1,0,0,1,0,0,0,1,0},
                                     {0,0,0,0,0,0,0,0,0,1,1},
                                     {0,0,0,1,1,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,1,1,0,0,0},
                                     {0,0,0,0,0,1,0,1,0,0,0}}, //Level 8
                                     
                                    {{0,1,0,1,0,0,0,0,0,0,0},
                                     {1,0,1,0,0,0,0,0,0,0,0},
                                     {0,1,0,0,1,0,0,0,0,0,0},
                                     {1,0,0,0,1,0,0,0,0,0,0},
                                     {0,0,1,1,0,0,0,1,1,0,0},
                                     {0,0,0,0,0,0,0,0,0,1,1},
                                     {0,0,0,0,0,0,0,1,1,1,1},
                                     {0,0,0,0,1,0,1,0,0,0,0},
                                     {0,0,0,0,1,0,1,0,0,0,0},
                                     {0,0,0,0,0,1,1,0,0,0,0},
                                     {0,0,0,0,0,1,1,0,0,0,0}}, //Level 9
                                     
                                    {{0,0,1,1,0,0,0,0,0,0,0,0,0},
                                     {0,0,1,0,1,0,0,0,0,0,0,0,0},
                                     {1,1,0,0,0,0,0,0,0,0,0,1,1},
                                     {1,0,0,0,1,0,0,0,0,0,0,0,0},
                                     {0,1,0,1,0,0,0,0,0,0,0,0,0},
                                     {0,0,0,0,0,0,1,1,0,0,0,0,0},
                                     {0,0,0,0,0,1,0,0,1,0,0,0,0},
                                     {0,0,0,0,0,1,0,0,0,1,0,0,0},
                                     {0,0,0,0,0,0,1,0,0,0,1,0,0},
                                     {0,0,0,0,0,0,0,1,0,0,1,0,0},
                                     {0,0,0,0,0,0,0,0,1,1,0,1,1},
                                     {0,0,1,0,0,0,0,0,0,0,1,0,0},
                                     {0,0,1,0,0,0,0,0,0,0,1,0,0}}}; // Level 10
                                     
                                                   
                          
   static int[][][]temp = new int [10][][];
   static Buttons [] select = new Buttons [numLevels];
   
   //These variables are used for the Create your own puzzle mini game
   static ArrayList<Integer>miniX = new ArrayList<Integer>();
   static ArrayList<Integer>miniY = new ArrayList<Integer>();
   static int miniMatrix[][]= new int[100][100];
   static int miniTemp[][]=new int [100][100];

   static int [][] allX = {{385,317,533,245,480,385},
                           {380, 220, 512, 516, 219, 380}, 
                           {261, 320, 501, 441, 501, 440, 261,261},
                           {220, 361, 500, 360, 500, 220, 500,220},
                           {230, 381, 520, 380, 380, 381, 460, 520, 241, 301,230},
                           {221, 220, 220, 440, 440, 441, 320, 321, 320, 560, 560,221},
                           {282, 281, 281, 280, 281, 280, 500, 501, 501, 501, 501,282},
                           {260, 501, 380, 581, 180, 441, 320, 381, 380, 260, 501,260},
                           {301, 462, 459, 301, 381, 380, 380, 220, 541, 580, 179,301},
                           {241, 480, 361, 281, 441, 360, 221, 500, 141, 581, 361, 121, 600,241}};
                           
   static int [][] allY = {{165,445,265,270,442,165},
                           {140, 219, 213, 385, 395, 140}, 
                           {262, 201, 199, 261, 401, 460, 460,262},
                           {160, 160, 161, 261, 259, 400, 401,160},
                           {225, 262, 223, 141, 499, 382, 320, 421, 422, 321,225},
                           {160, 280, 499, 501, 381, 280, 381, 281, 161, 381, 500,160},
                           {121, 200, 280, 359, 439, 520, 522, 441, 360, 279, 198,121},
                           {160, 160, 220, 361, 360, 321, 321, 421, 500, 500, 500,160},
                           {141, 139, 521, 521, 442, 342, 240, 302, 300, 422, 421,141},
                           {140, 141, 201, 500, 500, 441, 461, 460, 200, 202, 302, 340, 342,140}};
   
   int r = 50;
   static final int RADIUS = 10;
   static boolean hinted = false;
   
   Drawing draw = new Drawing();
   Image vertexx;
   Image create,puzzle,design;
   Image vertexMove;
   Image vertexClicked;
   Image pauseClicked;
   Image selectLevel;
   Image redo;
   Image arrow;
   Image redoPressed;
   Image one;
   Image play;
   Image smile;
   Image stroke;
   Image arrow2;
   Image levelSelect2;
   Image turtle;
   Image rod;
   Image levelPage;
   Image hint;
   Image doge;
   Image finish;
   Image win;
   Image home;
   Image cat;
   Image yu;
   Image pause;
   Image pausePage;
   Image rules;
   Image back;
   Image grid;
   Image miniFinish;
   
   /*** result ********************************************
   * Purpose: Determine whether the player has finished   *
   *          a puzzle.                                   *
   * Parameters: a (2D int array which stores information *                                
   *             about the paths on the graph)            *
   * Returns: whether or not the player has won           *
   *******************************************************/
   public static boolean result (int [] [] a)
   {
      for (int i = 0; i<a.length; i++){
         for (int j = 0; j<a[0].length;j++)
            if (a[i][j]==1)
               return false;
      }
      return true;
   }
   
   /*** pointinCircle ************************************
   * Purpose: Check whether the user is clicking within  *
   *          a certain vertex                           *
   * Parameters: x - the x coordinate of the vertex      *
   *             y - the y coordinate of the vertex      *
   *             r - the radius of the vertex            *
   * Returns: whether or not the has clicked the vertex  *
   ******************************************************/
   public static boolean pointinCircle (int x, int y, int r)
   {
      if (xx>x && xx<x+r && yy>y && yy<y+r)
         return true;
      else 
         return false;
   }
   
   /*** orderClicked *********************************************
   * Purpose: Store the order the player is clicking the         *
   *          vertices in the arrayList orders               *
   * Parameters: n - the # of vertices on the screen             *
   *             x - the x coordinates of all vertices           *
   *             y - the y coordinates of all vertices           *
   *             r - the radius of a vertex                      *
   * Returns: none                                               *
   **************************************************************/
   public static void orderClicked (int n, int[]x, int[]y, int r, int[][]b)
   {
      for (int i = 0; i<n-1; i++)
      {
         if (pointinCircle(x[i],y[i],50)==true)
         {  
            if (orders.size()==0)
               orders.add(i);
            else if (i!=orders.get(orders.size()-1)){
            
               if (b[i][orders.get(orders.size()-1)]==1){
                  b[i][orders.get(orders.size()-1)]=0;
                  b[orders.get(orders.size()-1)][i]=0;
                  orders.add(i);
               }
            }
         }
      }
   }
   
   //overridden method
   /*** orderClicked *********************************************
   * Purpose: Store the order the player is clicking the         *
   *          vertices in the arrayList orders               *
   * Parameters: n - the # of vertices on the screen             *
   *             x - the x coordinates of all vertices           *
   *             y - the y coordinates of all vertices           *
   *             r - the radius of a vertex                      *
   * Returns: none                                               *
   **************************************************************/
   public static void orderClicked (int n, ArrayList<Integer>x, ArrayList<Integer>y, int r, int[][]b)
   {
      for (int i = 0; i<n-1; i++)
      {
         if (pointinCircle(x.get(i)-25,y.get(i)-25,r)==true)
         {  
            if (orders.size()==0)
               orders.add(i);
            else if (i!=orders.get(orders.size()-1)){
            
               if (b[i][orders.get(orders.size()-1)]==1){
                  b[i][orders.get(orders.size()-1)]=0;
                  b[orders.get(orders.size()-1)][i]=0;
                  orders.add(i);
               }
            }
         }
      }
   }
      

   /**************copyArray*************************************
   * Purpose: store the values of array a into array b
   * Parameters:  a - the array to be copied                   *
   *              b - the array to be stored                   *
   * Returns: none                                             *
   ************************************************************/
   public static void copyArray (int [] [] a, int [] [] b)
   {
      for (int i = 0; i<a.length; i++)
         for (int j = 0; j<a[0].length; j++)
            b[i][j] = a[i][j];
   }

   /**************acceptable*************************************
   * Purpose: check if there is a path between two vertices
   * Parameters:  a - the vertices that are already connected                  *
                  b - the paths pattern of the level
   * Returns: whether it is acceptable to connect two vertices                                         
   ************************************************************/
   public static boolean acceptable (ArrayList<Integer> a, int [] []b)
   {
      if (b[a.get(a.size()-1)][a.get(a.size()-2)]==0){
         return false;
      }
      else{
         b[a.get(a.size()-1)][a.get(a.size()-2)]=0;
         b[a.get(a.size()-2)][a.get(a.size()-1)]=0;
         return true;
      }
   }
   
   public Image returnImage (String name)
   {
      String path = "/images/" + name;
      return Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(path));

   }
   
   public Main(){
      vertexx = this.returnImage("pixil-frame-0 (2).png");
      vertexMove = this.returnImage("pixil-gif-drawing (1).gif");
      vertexClicked = this.returnImage("pixil-frame-0.jpg");
      pause = this.returnImage("pause.jpg");
      selectLevel = this.returnImage("levelSelect.jpg");            //jump
      redo = this.returnImage("redo.png");
      redoPressed = this.returnImage("redoPressed.png");
      one = this.returnImage("one.png");
      stroke = this.returnImage("stroke.png");
      play = this.returnImage("play.png");
      play = play.getScaledInstance(200, 128, Image.SCALE_DEFAULT);
      doge = this.returnImage("doge.png");
      doge = doge.getScaledInstance(220, 192, Image.SCALE_DEFAULT);
      cat = this.returnImage("cat.png");
      cat = cat.getScaledInstance(165,150,Image.SCALE_DEFAULT);
      turtle = this.returnImage("turtle.png");
      turtle = turtle.getScaledInstance(105, 120, Image.SCALE_DEFAULT);
      finish = this.returnImage("finish.png");
      hint = this.returnImage("hint.png");
      levelSelect2 = selectLevel.getScaledInstance(180, 100, Image.SCALE_DEFAULT);
      arrow = this.returnImage("arrow.png");
      arrow=arrow.getScaledInstance(45,30,Image.SCALE_DEFAULT);  //jump
      rules = this.returnImage("rules.png");
      rules = rules.getScaledInstance(70, 64, Image.SCALE_DEFAULT);
      back = this.returnImage("back.png");
      back = back.getScaledInstance(163, 63, Image.SCALE_DEFAULT);
      yu = this.returnImage("yu.png");
      yu = yu.getScaledInstance(200,90,Image.SCALE_DEFAULT);
      pausePage = this.returnImage("pausePage.png");
      smile = this.returnImage("smile.png");
      smile = smile.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
      create = this.returnImage("create.png");
      puzzle = this.returnImage("puzzle.png");
      design  = this.returnImage("design.png");
      rod = this.returnImage("rod.png");
      rod = rod.getScaledInstance(60,50,Image.SCALE_DEFAULT);
      grid = this.returnImage("grid.png");
      grid = grid.getScaledInstance(600,525,Image.SCALE_DEFAULT);
      home = this.returnImage("home.png");
      arrow2 = this.returnImage("arrow2.png");
      arrow2 = arrow2.getScaledInstance(45, 30, Image.SCALE_DEFAULT);
      win = this.returnImage("Win.png");
      pauseClicked = this.returnImage("pressedpause.jpg");
      miniFinish = this.returnImage("miniFinish.png");
      
      //Set up JFrame
      JFrame frame = new JFrame("One Stroke");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(draw);
      draw.addMouseListener(new ClickHandler());
      frame.setSize(800,700);
      frame.setLocation (250,20);
      frame.setResizable(false);
      frame.setVisible(true);
      frame.getContentPane().setBackground(Color.white);
      
      for (int p = 0; p<temp.length; p++)
         temp[p] = new int [numVertex[p]][numVertex[p]];
       
       //initializing the array of Buttons (for select levels page)
      for (int i = 0; i<2; i++){
          for (int j = 0; j <5; j++){
              select[i*5+j] = new Buttons (140+110*j,380+75*i);
          }
       }
       copyArray(matrixVertices[0],temp[0]);
       
       //initially, only level 1 is unlocked.
       levels.add(1);
   }
   
   
   class ClickHandler extends MouseAdapter
   {
      public void mouseClicked (MouseEvent e)
      {
         xx = e.getX();
         yy = e.getY();
         
         //Mouse actions on the Home Page
         if (pageNum==2){
            //player clicks on "play" button
            if (xx>310 && yy>450 && xx<510 && yy<578){
               pageNum = 3;
               xx = 0;
               yy = 0;
            }
            //player clicks on "instructions" button ("?")
            if (xx>640 && yy>50 && xx<710 && yy<114){
               pageNum = 1;
               previousPage = 2;
               xx=0;
               yy=0;
               orders = new ArrayList<Integer>();              
            }
         }
         
         ////===============================================
         //Mouse actions on the instructions page
         if (pageNum ==1){
            level = 1;
            //player clicks on "back" button
            if (xx>20&&yy>20&&xx<183&&yy<83){
               pageNum = 2;
               orders = new ArrayList<Integer>();
               copyArray(temp[0],matrix);
               xx=yy=-1;
            }
         }
         
         //Check which level player selects 
         for (int i = 0 ;pageNum==3 && i<select.length ; i++){
            if (select[i].within (xx,yy)&&levels.indexOf(i+1)!=-1){
               level = i+1;     
               copyArray (matrixVertices[level-1],temp[level-1]); 
               pageNum = 4;   //switch to game page
               hinted = false;
               xx=0;
               yy=0;
            }
         }
         
         //===============================================
         //Select Levels page
         if (pageNum==3){
            //player returns to home page
            if (xx>236 && yy>545 && xx<389 && yy<595){
               pageNum = 2;
               xx=yy=-1;
            }
            //player goes to ""create puzzle"" page
            else if (xx>440 && xx<598 && yy>542 && yy<607){
               pageNum = 5;
               xx=yy=-1;
            }
         }
         
         //===============================================
         //Game Page
         if (pageNum==4){
            if (paused){
               //Redo the level
               if (xx>270&&xx<320 && yy>278&&yy<328)
               {  
                 orders = new ArrayList<Integer>();
                 copyArray(temp[level-1],matrix);
               }
               //hint button operations
               if (xx>360&&xx<515 && yy>280&&yy<316){
                  paused = false;
                  orders = new ArrayList<Integer>();
                  copyArray(temp[level-1],matrix);
                  orders.add(hints[level-1]);
                  hinted = true;
               }
               
               //Return to levels page
               if (xx>311&&xx<483 && yy>330&&yy<416)
               {
                  orders = new ArrayList<Integer>();
                  copyArray(temp[level-1],matrix);
                  pageNum = 3;
                  paused = false;
                  xx=yy=-1;
               }
            }
            //if the player solves this level, unlock two more levels
            if (orders.size()==numPaths[level-1]+1){
            
               levels.add(level+1);
               levels.add(level+2);
               //check if the "back" button is pressed
               if (xx>440 && xx<603 && yy>535 && yy<598)
               {
                  pageNum = 3;
                  orders = new ArrayList<Integer>();
                  copyArray(temp[level-1],matrix);
                  xx=-1;
                  yy=-1;
               }
            }  
         }
         //===============================================
         //Create Puzzle page
         else if (pageNum==5)
         {
            //player selects "add vertex"
            if (xx>150 && xx<200 && yy>60 && yy<110)
               click = true;
            if (click== true && xx>120 && xx<680 && yy>150 && yy<550){
               click = false;
               miniX.add(xx);
               miniY.add(yy); 
               numVertex[10]++;
               numPaths[10]++;   
               miniMatrix[numVertex[10]][numVertex[10]-1]=1;
               miniMatrix[numVertex[10]-1][numVertex[10]]=1;
               copyArray(miniMatrix,tempMatrix);
            }
            //"Draw path" operations
            if (numVertex[10]>2 && xx>300 && xx<360 && yy>70 && yy<120) 
               clickk = true;
            if (clickk == true && xx>120 && xx<680 && yy>150 && yy<550)
            {
               for (int i = 0; i<numVertex[10]; i++)
                  if (pointinCircle(miniX.get(i)-25,miniY.get(i)-25,50)==true)
                  {   
                     //player cannot move on with the game unless he chooses two vertices
                     if (t==-1) t = i;
                     else if (s==-1) s = i;
                  }
               if (s!=-1){
                  clickk = false;   
                  miniMatrix[t][s]=1;
                  miniMatrix[s][t]=1;
                  s=-1;
                  numPaths[10]++;
                  t=-1;
                  copyArray(miniMatrix,tempMatrix);
               }       
            }
            //"finished" button operations
            if (miniX.size()>0 && test==false && xx>316 && xx<575 && yy>583 &&yy<615){
               test = true;
               miniX.add(miniX.get(0));
               miniY.add(miniY.get(0));
            }
            //"back" button operations
            else if (test && xx>310 && xx<473 && yy>550 && yy<613)
            {
               test=false;
               orders = new ArrayList<Integer>();
               miniMatrix=new int[100][100];
               tempMatrix=new int[100][100];
               miniX=new ArrayList<Integer>();
               miniY=new ArrayList<Integer>();
               numVertex[10]=0;
               numPaths[10]=-1;
            } 
            //pause page operations
            if (paused){
               if (xx>307&&xx<487 && yy>262&&yy<297)
               {  
                 orders = new ArrayList<Integer>();
                 pageNum = 2;
                 paused = false;
               }
               if (xx>311&&xx<483 && yy>330&&yy<416)
               {
                  orders = new ArrayList<Integer>();
                  pageNum = 3;
                  paused = false;
               }
            }
         }
         draw.repaint();
      }
   }
   
   

   class Drawing extends JComponent
   {          
      public void paint(Graphics g)
      {      
         Graphics2D g2 = (Graphics2D) g;  
         
         //Draws a round-edged box with dashed lines as its border.
         final float dash1[] = {10.0f};
         final BasicStroke dashed = new BasicStroke(5.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f);
          //==================================================================================================================
        
         //instructions page and level 1 operations
         //they are set apart from the other levels due to their interactivity purpose
         if (level==1 && (pageNum==1 ||pageNum==4)){
         
            int[]x = allX[0];
            int[]y = allY[0];
            matrix = matrixVertices[0]; 
            
            if (pageNum==1){
               g.drawImage(back,20,20,this);
            }
            if (xx!=-1)
            {
               if (orders.size()==0 && pointinCircle(x[4],y[4],r)==true){
                  orders.add(4);
                  xx=-1;
               }
               else if (orders.size()==1 && pointinCircle(x[3],y[3],r)==true){
                  orders.add(3);
                  xx=-1;
               }
               else if (orders.size()==2 && pointinCircle(x[2],y[2],r)==true){
                  orders.add(2);
                  xx=-1;
               }
               else if (orders.size()==3 && pointinCircle(x[1],y[1],r)==true){
                  orders.add(1);
                  xx=-1;
               }
                  
               else if (orders.size()==4 && pointinCircle(x[0],y[0],r)==true){
                  orders.add(0);
                  xx=-1;
               }
               else if (orders.size()==5 && pointinCircle(x[4],y[4],r)==true){
                  orders.add(4);
                  xx=-1;
                  finished = true;
               }
               
            }
            //if all the paths are connected, and it is instructions page
            if (orders.size()==6 && pageNum!=1)
            {
               g.drawImage(win,150,50,this);
               g.drawImage(back,440,535,this);
               
               //check if the "redo" button is pressed
               if (xx>=330 && xx<380 && yy>550 && yy<600){
                  g.drawImage (redoPressed, 330,550,this);
                  orders = new ArrayList<Integer>();
                  copyArray(temp[level-1],matrix);
               }
               else{
                  g.drawImage (redo,330,545,this);
               }
            } 
            //all paths are connected, and it is level 1
            if (orders.size()!=6 && pageNum==4){
               if (xx>=700 && xx<750 && yy>50&& yy<100){
                  g.drawImage (pauseClicked, 700,50,this);
                  paused = true;
               }
               else{
                  g.drawImage(pause, 700, 45, this);
                  paused = false;
               }
               //check if the "redo" button is pressed
               if (xx>=620 && xx<670 && yy>50 && yy<100){
                  g.drawImage (redoPressed, 620,50,this);
                  orders = new ArrayList<Integer>();
                  copyArray(temp[level-1],matrix);
               }
               else{
                  g.drawImage (redo,620,45,this);
               }
            }
            //guide the user as they gradually finish the puzzle
            if (orders.size()==1)
            {
               g.drawImage(doge,100,230,this);
            }
            if (orders.size()>=0 && orders.size()<6)
            {
               g.setFont(new Font("TimesRoman", Font.BOLD, 25));
               g.drawString("Imagine yourself drawing a shape on a piece of paper",130,110);
            }
            if (orders.size()==0)
               g.drawImage(cat,530,450,this);
            if (orders.size()>=3 && orders.size()<6)
            {
               g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
               g.drawString("To finish this puzzle, draw the shape in one stroke",200,140);
            }
            if (orders.size()==2)
               g.drawImage(arrow,600,270,this);
            if (orders.size()==3)
               g.drawImage(arrow2,250,470,this);
            if (orders.size()==4)
               g.drawImage(arrow,460,165,this);
            if (orders.size()==5){
               g.drawImage(turtle,540,460,this);
               g.drawImage(miniFinish.getScaledInstance(100, 36, Image.SCALE_DEFAULT),540,430,this);
            }
            
            //This for loop draws pathways between the vertices.
            for (int i = 0; i<matrix.length; i++){
               for (int j = 0; j<matrix[0].length; j++){
                  //Set the stroke thickness of the pathways to 18 pixels.
                  g2.setStroke(new BasicStroke(18f));
                  if (matrix[i][j]==1){
                    g.setColor(new Color(200,200,200));
                    g.drawLine(x[i]+(r/2),y[i]+(r/2),x[j]+(r/2),y[j]+(r/2));
                  }
               }
            }

            //Color the visited paths yellow
            g.setColor(new Color(243,193,58));
            for (int l = 0; l<orders.size()-1; l++)
            {
               g.drawLine(x[orders.get(l)]+(r/2),y[orders.get(l)]+(r/2),
                          x[orders.get(l+1)]+(r/2),y[orders.get(l+1)]+(r/2));
            }

            //This for loops draws the unvisited vertices.
            g.setColor(new Color(182,215,168)); 
           
            for (int i = x.length-1; i>=0; i--){
               if (orders.indexOf(i)==-1) 
                  if (i!=0)
                     g.drawImage(vertexx, x[i], y[i], this);
            }
            
            //Vertices turn pink once clicked upon.
            for (int h = 0; h<orders.size(); h++)
               if (vertexClicked!=null)
                  g.drawImage(vertexClicked,x[orders.get(h)],y[orders.get(h)]+4,this);
            
            //Player pauses the page
            if (paused){ 
                  g.drawImage(pausePage,200,160,this);
                  g.drawImage(yu,300,140,this);
                  g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                  g.setColor(new Color(0,0,0));
                  g.drawString("Click anywhere to continue",290,450);
                  g.drawImage(redo,270,278,this);
                  g.drawImage(levelSelect2,311,330,this);
                  g.drawImage(hint,360,280,this); 
            }
          
         }
         //==================================================================================================================
         else if (pageNum ==2){
         //formatting the home page
            g.drawImage(one,250,110,this);
            g.drawImage(stroke,140,260,this);
            g.drawImage (play, 310,450,this);
            g.drawImage (rules,640,50,this);
         }
         //==================================================================================================================
         else if (pageNum == 3){
            //levels select page.
            
            g2.setStroke(dashed);
            g.drawImage (selectLevel,200,70,this);
            
            //draw the levels buttons
            for (int i = 0; i<2; i++){
               for (int j = 0; j <5; j++){
                  if (levels.indexOf(i*5+j+1)!=-1)
                     g.setColor (new Color(58, 177, 216)); 
                  else
                     g.setColor(new Color(200,200,200));
                  g2.draw(new RoundRectangle2D.Double(140+110*j,380+75*i,80,45,10, 10));
                  Font myFont = new Font("Serif", Font.BOLD, 25);
                  g.setFont(myFont);
                  g.drawString(Integer.toString(i*5+j+1),175+110*j,410+75*i);
               }
            }
            
            //draw other buttons for various functions
            g.drawImage(back,230,540,this);
            g.drawImage(create,440,540,this);
            g.drawImage(puzzle,440,580,this);

         }
         //==================GAME PAGE==========================================================================================
         else if (pageNum == 4){
         //
            int[]x = allX[level-1];
            int[]y = allY[level-1];
            matrix = matrixVertices[level-1]; 
            
            if (xx != -1){
               //this method automatically adds the clicked Vertex to the ArrayList "orders"
               orderClicked(x.length, x,y, r/2+1,matrix);
               
               //check if the player finished the puzzle
               if (orders.size()>=2 && orders.size()==numPaths[level-1]+1){
                  if (result(matrix)){
                     resulted = true;
                     levels.add(level+1);
                     levels.add(level+2);
                  } 
               }
               
               //player clicks ""hints" from the pause page
               if (hinted)
               {
                  g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                  g.setColor(new Color(0,0,0));
                  g.drawString ("The suggested start point has been highlighted",200,70);
               }
               
               //player has won
               if (orders.size()==numPaths[level-1]+1)
               {
                  g.setColor(new Color(255,255,255));
                  g.fillRect(150,50,500,72);
                  g.drawImage(win,150,50,this);
                  g.drawImage(back,440,535,this);
               
                  //check if the "redo" button is pressed
                  if (xx>=330 && xx<380 && yy>550 && yy<600){
                     g.drawImage (redoPressed, 330,550,this);
                     orders = new ArrayList<Integer>();
                     copyArray(temp[level-1],matrix);
                  }
                  else{
                     g.drawImage (redo,330,545,this);
                  }
               } 
               
               //game still playing
               else{
                  if (xx>=700 && xx<750 && yy>50&& yy<100){
                    g.drawImage (pauseClicked, 700,50,this);
                    paused = true;
                  }  
                  else{
                     g.drawImage(pause, 700, 45, this);
                     paused = false;
                  }
                  //check if the "redo" button is pressed
                  if (xx>=620 && xx<670 && yy>50 && yy<100){
                     g.drawImage (redoPressed, 620,50,this);
                     orders = new ArrayList<Integer>();
                     copyArray(temp[level-1],matrix);
                  }
                  else{
                     g.drawImage (redo,620,45,this);
                  }
               }
            }
            
            //This for loops draws pathways between the vertices.
            for (int i = 0; i<matrix.length; i++){
               for (int j = 0; j<matrix[0].length; j++){
                  //Set the stroke thickness of the pathways to 18 pixels.
                  g2.setStroke(new BasicStroke(18f));
                  
                  if (matrix[i][j]==1){
                    g.setColor(new Color(200,200,200));
                    g.drawLine(x[i]+(r/2),y[i]+(r/2),x[j]+(r/2),y[j]+(r/2));
                  }
               }
            }

            //Color the visited paths yellow
            g.setColor(new Color(243,193,58));
            for (int l = 0; l<orders.size()-1; l++)
            {
               g.drawLine(x[orders.get(l)]+(r/2),y[orders.get(l)]+(r/2),
                          x[orders.get(l+1)]+(r/2),y[orders.get(l+1)]+(r/2));
            }

            //This for loops draws the vertices.
            g.setColor(new Color(182,215,168)); 
           
            for (int i = x.length-1; i>=0; i--){
               if (orders.indexOf(i)==-1) 
                  if (i!=0)
                     g.drawImage(vertexx, x[i], y[i], this);
            }
            
            
            //Clicked vertices turn pink
            for (int h = 0; h<orders.size(); h++)
            {
               if (vertexClicked!=null)
                  g.drawImage(vertexClicked,x[orders.get(h)],y[orders.get(h)]+4,this);
            }    
                     
            //player pauses the game
            //draw pause game page buttons
            if (paused){ 
                  g.drawImage(pausePage,200,160,this);
                  g.drawImage(yu,300,140,this);
                  g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                  g.setColor(new Color(0,0,0));
                  g.drawString("Click anywhere to continue",290,450);
                  g.drawImage(redo,270,278,this);
                  g.drawImage(levelSelect2,311,330,this);
                  g.drawImage(hint,360,280,this); 
            }
         }
         
         //==================================================================================================
         //Create puzzle page
         if(pageNum == 5)
         {
            //if the player is still creating the puzzle:
            if (!test){
               g.setColor(new Color(200,200,200));
               //draw vertical lines for the grid
               for (int i = 120; i<680; i+=20)    
                  g.drawLine (i,150,i,550);
               
               //draw vertical lines for the grid
               for (int i =150;i<550; i+=20)    
                  g.drawLine (120,i,680,i);
               
               //set up the page
               //different buttons
               g.setColor(new Color (0,0,0));
               g2.setStroke(dashed);
               g2.draw(new RoundRectangle2D.Double(120, 150,560,400,10, 10));
               g.setFont(new Font("Ar Christy", Font.PLAIN, 20));
               g.drawImage(vertexx,150,70,this);
               g.drawString("Add vertex",140,60);
               g.drawImage(rod,300,70,this);
               g.drawString("Add path",300,60);
               g2.draw(new RoundRectangle2D.Double(300, 570,285,60,10, 10));
               g.drawImage (finish, 316,583,this);
               
               //player erases the design
               if (xx>=600 && xx<650 && yy>60 && yy<110){
                  g.drawImage (redoPressed, 600,60,this);
                  miniMatrix = new int[100][100];
                  miniX = new ArrayList<Integer>();
                  miniY = new ArrayList<Integer>();
                  t=s=-1;
                  click=clickk=false;
                  numVertex[10]=0;
                  numPaths[10]=-1;
               }
               else{
                  g.drawImage (redo,600,55,this);
               }
            
               //simple instructions to guide player
               if (click)
                  g.drawString("Click on canvas to add Vertex",250,180);
               if (clickk)
                  g.drawString("Click on two vertices",300,180);
            }
            //if it is the "test" page
            else{
               orderClicked(miniX.size(), miniX,miniY, 50,tempMatrix);
               g.drawImage(design,40,60,this);
               g.drawImage(back,310,550,this);
                        
               //check if the "redo" button is pressed
               if (xx>=620 && xx<670 && yy>50 && yy<100){
                  g.drawImage (redoPressed, 620,50,this);
                  orders = new ArrayList<Integer>();
                  copyArray(miniMatrix,tempMatrix);
               }
               else{
                  g.drawImage (redo,620,45,this);
               }
            }
            
            //check if the "pause" button is pressed
            if (xx>=700 && xx<750 && yy>50&& yy<100){
               g.drawImage (pauseClicked, 700,50,this);
               paused = true;
            }  
            else{
               g.drawImage(pause, 700, 45, this);
               paused = false;
            }
            
            //This for loops draws pathways between the vertices.
            for (int i = 0; i<numVertex[10]; i++){
               for (int j = 0; j<numVertex[10]; j++){
                  //Set the stroke thickness of the pathways to 18 pixels.
                  g2.setStroke(new BasicStroke(18f));
                  
                  if (tempMatrix[i][j]==1){
                    g.setColor(new Color(200,200,200));
                    g.drawLine(miniX.get(i)+(r/2)-25,miniY.get(i)+(r/2)-25,miniX.get(j)+(r/2)-25,miniY.get(j)+(r/2)-25);
                  }
               }
            }

            //Color the visited paths yellow
            g.setColor(new Color(243,193,58));
            for (int l = 0; l<orders.size()-1; l++)
            {
               g.drawLine(miniX.get(orders.get(l))+(r/2)-25,miniY.get(orders.get(l))-25+(r/2),
                          miniX.get(orders.get(l+1))+(r/2)-25,miniY.get(orders.get(l+1))-25+(r/2));
            }

            //This for loops draws the vertices.
            g.setColor(new Color(182,215,168)); 
           
            for (int i = miniX.size()-1; i>=0; i--){
               if (orders.indexOf(i)==-1) 
                     g.drawImage(vertexx, miniX.get(i)-25, miniY.get(i)-25, this);
            }
            
            //Clicked vertices turn pink
            for (int h = 0; h<orders.size(); h++)
            {
               if (vertexClicked!=null)
                  g.drawImage(vertexClicked,miniX.get(orders.get(h))-25,miniY.get(orders.get(h))-25+4,this);
            }    
                     
            //draw the pause page
            if (paused){ 
                  g.drawImage(pausePage,200,160,this);
                  g.drawImage(yu,300,140,this);
                  g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                  g.setColor(new Color(0,0,0));
                  g.drawString("Click anywhere to continue",290,450);
                  g.drawImage(levelSelect2,311,330,this);
                  g.drawImage(home,310,260,this); 
            }
         }
      }
   }
   
   public static void main(String[] args)
   {
      new Main();
   }
   
}




