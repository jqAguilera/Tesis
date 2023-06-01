package cl.jdcsolutions.p_bike.Objetos;

public class Bicicleta {

    private String marca;
    private String color;

    public Bicicleta(String marca, String color) {
        this.marca = marca;
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
