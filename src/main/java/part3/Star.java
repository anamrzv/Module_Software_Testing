package part3;

public class Star extends CosmicBody {

    private Moon pair = null;
    private boolean isShining = false;
    private boolean isCrawling = false;
    private boolean isSleeping = false;

    public Star(Color color, Size size, String name) {
        super(color, size, name);
    }

    @Override
    public void shine() {
        if (!isSleeping) {
            System.out.printf("В первый момент показалось, что ничего не произошло, затем звезда %s засветилась на краю огромного экрана.%n", this.getName());
            isShining = true;
        } else System.out.printf("%s звезда спит, нельзя выполнить действие%n", this.getName());

    }

    @Override
    public void crawl() {
        if (!isSleeping) {
            isCrawling = true;
            System.out.printf("%s, имеющая %s цвет, звезда %s ползла по экрану%n", this.getSize(), this.getColor(), this.getName());
        } else System.out.printf("%s звезда спит, нельзя выполнить действие%n", this.getName());
    }

    @Override
    public void sleep(int time) {
        System.out.printf("%s звезда спит%n", this.getName());
        isSleeping = true;
        isCrawling = false;
        isShining = false;
    }

    @Override
    public void wake() {
        System.out.printf("%s звезда проснулась%n", this.getName());
        isSleeping = false;
    }

    public boolean addPairMoon(Moon moon) {
        if (moon == null) throw new RuntimeException("Луна не может быть null!");
        if (moon.isSleeping()) {
            System.out.println("Спящая луна не может быть добавлена в пару");
            return false;
        }
        else if (moon.equals(pair)) {
            System.out.println("Луна уже состоит в паре");
            return false;
        }
        pair = moon;
        moon.becomeFriends();
        return true;
    }
}
