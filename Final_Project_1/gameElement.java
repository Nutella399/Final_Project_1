package cs3700;

import java.util.Random;

public class gameElement {

    enum element {Rock, Paper, Scissor};
    int points;
    element A;

    public gameElement() {
        points = 0;
    }

    public void pick() {
        Random rand = new Random();
        int x = rand.nextInt(3);
        switch (x) {
            case 0:
                A = element.Rock;
                break;
            case 1:
                A = element.Paper;
                break;
            case 2:
                A = element.Scissor;
                break;
        }
    }

    public void getResult (element B, element C){
        switch (A) {
            case Rock:
                if(B == element.Scissor) {
                    points++;
                    if (C == element.Scissor)
                        points++;
                } else if (C == element.Scissor)
                    points++;
                break;
            case Paper:
                if(B == element.Rock) {
                    points++;
                    if (C == element.Rock)
                        points++;
                } else if (C == element.Rock)
                    points++;
                break;
            case Scissor:
                if(B == element.Paper) {
                    points++;
                    if (C == element.Paper)
                        points++;
                } else if (C == element.Paper)
                    points++;
                break;
        }
    }

    public element getElement () {
        return A;
    }

    public int getPoints() {
        return points;
    }
}
