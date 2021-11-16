import javax.swing.*;

public class Home extends JPanel {

    int countX=20;
    int countY=12;
    int size=30;

    Floor[][] floors=new Floor[countX][countY];


    int wallCountX=countX+2;
    int wallCountY=countY+2;
    Wall[][] walls=new Wall[wallCountX][wallCountY];

    Element transfer=new Element("picture/传送门.png",9*size,0*size,5*size,size);
    Element box;
    Element books;
    Element sofa;
    Element table;

    Role knight;//骑士
    Role paladin;//圣骑士
    Role assassin;//刺客
    Role ranger;//游侠
    Role sharpshooter;//神射手
    Role werewolf;//狼人
    Role priest;//牧师
    Role vampire;//吸血鬼
    Role master;//法师
    Role alchemist;//炼金术士


    
    boolean connectUP=true;
    boolean connectDown=false;
    boolean connectLeft=false;
    boolean connectRight=false;

    public Home(){
        initFloors();

        knight=new Role("picture/Role/knight_1.png",floors[15][0]);
        paladin=new Role("picture/Role/paladin.png",floors[14][0]);
        assassin=new Role("picture/Role/assassin.png",floors[10][6]);
        ranger=new Role("picture/Role/ranger.png",floors[14][6]);
        sharpshooter=new Role("picture/Role/sharpshooter.png",floors[12][5]);
        werewolf=new Role("picture/Role/werewolf.png",floors[14][9]);
        priest=new Role("picture/Role/priest.png",floors[3][0]);
        vampire=new Role("picture/Role/vampire.png",floors[3][5]);
        master=new Role("picture/Role/master.png",floors[5][5]);
        alchemist=new Role("picture/Role/alchemist.png",floors[7][5]);

        box=new Element("picture/box.png",floors[7][0].getBoundsX(),floors[7][0].getBoundsY(),size,size);
        books=new Element("picture/books.png",floors[5][8].getBoundsX(),floors[5][8].getBoundsY(),size,size);
        sofa=new Element("picture/沙发.png",floors[13][0].getBoundsX(),floors[13][0].getBoundsY(),4*size,2*size);
        table=new Element("picture/桌子.png",floors[9][4].getBoundsX(),floors[9][4].getBoundsY(),3*size,3*size);


    //    knight=new Role("picture/Role/knight_1.png")
        setLayout(null);

        add(knight);
        add(paladin);
        add(assassin);
        add(ranger);
        add(sharpshooter);
        add(werewolf);
        add(priest);
        add(vampire);
        add(master);
        add(alchemist);


        add(transfer);
        add(box);
        add(books);
        add(sofa);
        add(table);
        buildFloor();
        buildWall();
        setVisible(true);
    }

    public Home(int countX,int countY){
        this.setLayout(null);
        this.countX=countX;
        this.countY=countY;
        buildFloor();
        buildWall();
        this.setVisible(true);
    }
    public void buildFloor(){
        for(int x=0;x<countX;x++){
            for(int y=0;y<countY;y++){
                this.add(floors[x][y]);
            }
        }
    }
    public void buildWall(){
        int wallCountX=countX+2;
        int wallCountY=countY+2;
        Wall[][] walls=new Wall[wallCountX][wallCountY];
        for(int x=0;x<wallCountX;x++){//上下两边墙
            if(x==0 || x==wallCountX-1){
                walls[x][0]=new Wall(x,0,0);
                this.add(walls[x][0]);
            }
            else{
                walls[x][0]=new Wall(x,0,1);
                this.add(walls[x][0]);
            }
            walls[x][wallCountY-1]=new Wall(x,wallCountY-1,1);
            this.add(walls[x][wallCountY-1]);
        }
        for(int y=1;y<wallCountY-1;y++){//左右两边墙
            walls[0][y]=new Wall(0,y,0);
            this.add(walls[0][y]);
            walls[wallCountX-1][y]=new Wall(wallCountX-1,y,0);
            this.add(walls[wallCountX-1][y]);
        }
        if(connectUP==true){
            int connectUPBegin=wallCountX/2-1;
            int connectUpEnd=connectUPBegin+2;
            for(int i=connectUPBegin;i<=connectUpEnd;i++){
                this.remove(walls[i][0]);
            }
        }


    }
    public void initFloors(){
        for(int x=0;x<countX;x++){
            for(int y=0;y<countY;y++){
                floors[x][y]=new Floor(x,y);
            }
        }
    }

}
