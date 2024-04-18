package MentesMatematicas.Quimica;


public interface Unit {
    public abstract void setCoefficient(int coefficient);
    public abstract int getCoefficient();
    public abstract String getHtml();
    public abstract int getElementCount(String symbol);
}
