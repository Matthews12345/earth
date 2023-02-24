public class earth extends Canvas {

    static int width = 800, height = 800;//размеры окна

    public static void main(String[] args) throws IOException {


        //ДЛЯ ПОЯВЛЕНИЯ ОКОШКА С НУЖНЫМИ НАМ ПАРАМЕТРАМИ РАЗМЕРА
        JFrame frame = new JFrame();
        frame.setVisible(true); // окошко
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //для его закрытия
        frame.setTitle("головные планеты");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height + 30);
        frame.getContentPane().setLayout(null);//выравнивание, чтобы координаты объектов в дальнейшем считались от верхнего левого угла
        frame.getContentPane().setBackground(Color.BLACK);//фон окна


        //ДОБАВЛЕНИЕ КАРТИНКИ И УСТАНОВКА ПОЯВЛЕНИЯ
        BufferedImage screamHead = ImageIO.read(new URL("https://tgram.ru/wiki/stickers/img/rmrick/png/6.png"));//скачиваем картинку
        BufferedImage sunnyHead = ImageIO.read(new URL("https://tgram.ru/wiki/stickers/img/rmrick/png/6.png"));
        int screamHeadWidth = screamHead.getWidth(); //запоминаем ее размер (понадобится, когда будем двигать JLabel)
        int screamHeadHeight = screamHead.getHeight();//запоминаем ее размер (понадобится, когда будем двигать JLabel)
        int sunnyHeadWidth = sunnyHead.getWidth();
        int sunnyHeadHeight = sunnyHead.getHeight();
        JLabel wIcon = new JLabel(new ImageIcon(screamHead));//создаем объект с картинкой, который будем размещать и двигать
        JLabel wIcon2 = new JLabel(new ImageIcon(sunnyHead));
        int startX = width / 2 - screamHeadWidth / 2, startY = height / 20;//задаём координаты для движущейся картинки
        int newStartX = width / 2 - sunnyHeadWidth / 2, newStartY = height / 2 - sunnyHeadHeight / 2; //координаты для картинки по центру
        wIcon.setBounds(newStartX, newStartY, sunnyHeadWidth, sunnyHeadHeight);//устанавливаем начальное положение картинки, которая по центру
        wIcon2.setBounds(startX,startY,screamHeadWidth,screamHeadHeight);//координаты для головы, которая будет кружить
        frame.add(wIcon);//добавляем картинку на форму
        frame.add(wIcon2);//картинка по центру


        int radius=Math.max(width, height)/2-Math.max(sunnyHeadWidth, sunnyHeadHeight);// находим оптимальный радиус окружности
        int deltaX=width/2-sunnyHeadWidth/2, deltaY=height/2-sunnyHeadHeight/2;//константы для смещения картинки, чтобы не рассчитывать их постоянно

        //ДЕЛАЕМ БЕСКОНЕЧНЫЙ ЦИКЛ ПЕРЕМЕЩЕНИЯ КАРТИНКИ
        for (int t = 0; t < 360;) {
            try {//сперва работает таймер
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            wIcon2.setBounds((int) (radius*Math.cos(t*Math.PI/180))+deltaX, (int) (radius*Math.sin(t*Math.PI/180))+deltaY, sunnyHeadWidth, sunnyHeadHeight);//перемещаем картинку в нужную точку, которую вычисляем по формуле параметрических уравнений линий, приводя градусы t к радианам
            wIcon2.repaint();//перерисовываем картинку
            t=t==359?0:t+1;//этой формулой обеспечиваем постоянный цикл и обнуление t при 359
        }

    }


}
