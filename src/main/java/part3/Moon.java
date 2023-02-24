package part3;

public class Moon extends CosmicBody {

    public Moon(Color color, Size size, String name) {
        super(color, size, name);
    }

    @Override
    public void shine() {
        System.out.println(String.format("Луна %s издала %s свет, переходящий в черноту -- ночная сторона планеты", this.getName(), this.getColor()));
    }

    @Override
    public void crawl() {
        System.out.println(String.format("%s луна %s возникла в угру картинки", this.getSize(), this.getName()));
    }
}
