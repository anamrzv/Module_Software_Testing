package part3;

public class Star extends CosmicBody {

    public Star(Color color, Size size, String name) {
        super(color, size, name);
    }

    @Override
    public void shine() {
        System.out.println(String.format("В первый момент показалось, что ничего не произошло, затем звезда %s засветилась на краю огромного экрана.", this.getName()));
    }

    @Override
    public void crawl() {
        System.out.println(String.format("%s, имеющая %s цвет, звезда %s ползла по экрану", this.getSize(), this.getColor(), this.getName()));
    }
}
