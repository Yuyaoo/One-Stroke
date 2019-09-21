public class Buttons {
   //x-the x coordinate of the button
   //y-the y coordinate of the button
   int x;
   int y;
   
   //constructor - stores the coordinates in the parameters
   public Buttons(int a, int b){
      x = a;
      y = b;
   }
   
   /*** within *********************************************
   * Purpose: Check whether a button is clicked                                *
   * Parameters: coordinates of the mouse                               *
   * Returns: boolean - whether a button is clicked                                      *
   ******************************************************/
   public boolean within (int a, int b){
      return (a>x && a<x+80 && b>y && b<y+45);
   }
}

