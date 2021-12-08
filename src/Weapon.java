import javax.swing.*;

public class Weapon extends JLabel {
    int id;
    int type;//武器种类,0为手枪,1为冲锋枪,2为刀剑
    int attack;
    int attackRate;
    int CRI;
    int cost;
    String name;
    Weapon(int id){
        switch (id){
            case 0:
                type=0;
                name="简陋的手枪";;


        }
    }
    void use(){

    }
    void BePicked(Player p){

    }
}
