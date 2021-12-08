import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Home extends JPanel implements MoveDistance{

    int countX=21;
    int countY=13;
    int size=50;
    //摄像头的大小
    int width=1200;
    int height=750;

    int x=0;
    int y=0;

    Floor[][] floors=new Floor[countX][countY];


    int wallCountX=countX+2;
    int wallCountY=countY+2;
    Wall[][] walls=new Wall[wallCountX][wallCountY];

    Element transfer=new Element("picture/传送门.png",9*size,0*size,5*size,size);
    Element box;
    Element books;
    Element sofa;
    Element table;

    ArrayList<Element> elements=new ArrayList<>();

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

    ArrayList<Role> roles=new ArrayList<>();

    Player p=new Player();


    
    boolean connectUP=true;
    boolean connectDown=false;
    boolean connectLeft=false;
    boolean connectRight=false;

    public Home(){
        initFloors();

        knight=new Role("picture/Role/knight_1.png",floors[15][1]);
        roles.add(knight);
        paladin=new Role("picture/Role/paladin.png",floors[14][1]);
        roles.add(paladin);
        assassin=new Role("picture/Role/assassin.png",floors[10][7]);
        roles.add(assassin);
        ranger=new Role("picture/Role/ranger.png",floors[14][6]);
        roles.add(ranger);
        sharpshooter=new Role("picture/Role/sharpshooter.png",floors[12][5]);
        roles.add(sharpshooter);
        werewolf=new Role("picture/Role/werewolf.png",floors[14][9]);
        roles.add(werewolf);
        priest=new Role("picture/Role/priest.png",floors[3][0]);
        roles.add(priest);
        vampire=new Role("picture/Role/vampire.png",floors[3][5]);
        roles.add(vampire);
        master=new Role("picture/Role/master.png",floors[5][5]);
        roles.add(master);
        alchemist=new Role("picture/Role/alchemist.png",floors[7][5]);
        roles.add(alchemist);



        box=new Element("picture/box.png",floors[7][0].getBoundsX(),floors[7][0].getBoundsY(),size,size);
        elements.add(box);
        books=new Element("picture/books.png",floors[5][8].getBoundsX(),floors[5][8].getBoundsY(),size,size);
        elements.add(books);
        sofa=new Element("picture/沙发.png",floors[13][0].getBoundsX(),floors[13][0].getBoundsY(),4*size,2*size);
        elements.add(sofa);
        table=new Element("picture/桌子.png",floors[9][4].getBoundsX(),floors[9][4].getBoundsY(),3*size,3*size);
        elements.add(table);

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
//添加角色
        add(p);

        buildFloor();
        buildWall();

        setKeyBindings();

        setVisible(true);
    }
    void moveRight(){
        x-=disX;
        this.setBounds(x,y,width,height);
    }
    void moveLeft(){
        x+=disX;
        this.setBounds(x,y,width,height);
    }
    void moveUp(){
        y+=disY;
        this.setBounds(x,y,width,height);
    }
    void moveDown(){
        y-=disY;
        this.setBounds(x,y,width,height);
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

    //移动
    private void setKeyBindings() {
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition );

        String vkLeft = "a";
        String vkRight = "d";
        String vkUp="w";
        String vkDown="s";
        String attack="j";
        String swap="k";
        String skill="u";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), vkLeft);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), vkRight);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0),vkUp);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0),vkDown);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_J,0),attack);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K,0),swap);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U,0),skill);

        actionMap.put(vkLeft, new KeyAction(vkLeft));
        actionMap.put(vkRight, new KeyAction(vkRight));
        actionMap.put(vkUp, new KeyAction(vkUp));
        actionMap.put(vkDown, new KeyAction(vkDown));
        actionMap.put(attack,new KeyAction(attack));
        actionMap.put(swap,new KeyAction(swap));
        actionMap.put(skill,new KeyAction(skill));


    }
    class KeyAction extends AbstractAction {
        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt) {
            System.out.println(actionEvt.getActionCommand() + " pressed");
            switch (actionEvt.getActionCommand()){
                case "a":
                    if(checkCanMove(p.x-disX,p.y,p.width,p.height)){
                        p.moveLeft();
                        moveLeft();
                    }
                    break;

                case "d":
                    if(checkCanMove(p.x+disX,p.y,+p.width,p.height)){
                        p.moveRight();
                        moveRight();
                    }
                    break;
                case "w":
                    if(checkCanMove(p.x,p.y-disY,p.width,p.height)){

                        p.moveUp();
                        moveUp();
                    }
                    break;
                case "s":
                    if(checkCanMove(p.x,p.y+disY,p.width,p.height)){

                        p.moveDown();
                        moveDown();
                    }
                    break;
                default:
                    System.out.println("输入错误");
            }
        }
    }

    public boolean checkCanMove(int x,int y,int width,int height){
        for(int i = 0;i < elements.size(); i ++){
            if(elements.get(i).check(x,y,width,height)){

            }
            else {
                return false;
            }
        }
        for(int i = 0;i < roles.size(); i ++){
            if(roles.get(i).check(x,y,width,height)){

            }
            else {
                return false;
            }
        }
        //x*width,y*width,width,height
        Wall w0=new Wall(0,0,0);
        Wall w1=new Wall(0,0,1);
        if(x>((walls[0][0].x+1)*w0.width) && (x+width)<walls[wallCountX-1][wallCountY-1].x*w0.width && y>w0.height && (y+height)<walls[wallCountX-1][wallCountY-1].y*w0.height){
            return true;
        }
        else{

            return false;
        }


    }

}

class KeyAction extends AbstractAction {
    public KeyAction(String actionCommand) {
        putValue(ACTION_COMMAND_KEY, actionCommand);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvt) {
        System.out.println(actionEvt.getActionCommand() + " pressed");

    }
}

