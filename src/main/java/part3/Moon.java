package part3;

public class Moon extends CosmicBody {

    private int energy = 3;
    private boolean isShining = false;
    private boolean isCrawling = false;
    private boolean isSleeping = false;

    public Moon(Color color, Size size, String name) {
        super(color, size, name);
    }

    @Override
    public void shine() {
        if (!isSleeping) {
            if (energy <= 0)
                System.out.printf("У луны %s недостаточно энергии на действие%n", getName());
            else {
                System.out.printf("Луна %s издала %s свет, переходящий в черноту -- ночная сторона планеты%n", this.getName(), this.getColor());
                isShining = true;
                energy -= 1;
            }
        } else System.out.printf("Луна %s спит, нельзя выполнить действие%n", getName());
    }

    @Override
    public void crawl() {
        if (!isSleeping) {
            if (energy <= 0)
                System.out.printf("У луны %s недостаточно энергии на действие%n", getName());
            else {
                System.out.printf("%s луна %s возникла в углу картинки%n", getSize(), getName());
                isCrawling = true;
                energy -= 2;
            }
        } else System.out.printf("Луна %s спит, нельзя выполнить действие%n", this.getName());
    }

    public void becomeFriends() {
        if (energy <= 0) {
            System.out.printf("У луны %s недостаточно энергии на действие%n", getName());
        } else energy -= 2;
    }

    public void sleep(int time) {
        if (time > 0) {
            System.out.printf("%s луна спит%n", this.getName());
            isSleeping = true;
            isCrawling = false;
            isShining = false;
            energy += time * 2;
        } else System.out.println("Неправильный аргумент, луна не может уснуть:(");
    }

    public void wake() {
        System.out.printf("%s луна проснулась%n", this.getName());
        isSleeping = false;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public boolean isShining() {
        return isShining;
    }

    public boolean isCrawling() {
        return isCrawling;
    }


}
