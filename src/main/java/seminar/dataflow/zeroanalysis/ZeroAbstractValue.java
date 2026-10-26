package seminar.dataflow.zeroanalysis;

/**
 * This enum represents the possible values of the zero analysis for a variable.
 */
public enum ZeroAbstractValue {

    /**
     * We don't have information about the variable.
     */
    BOTTOM("bottom"),

    /**
     * The variable is positive.
     */
    POSITIVE("positive"),

    /**
     * The variable is zero.
     */
    ZERO("zero"),

    /**
     * The variable is negative.
     */
    NEGATIVE("negative"),

    /**
     * The variable is top (can be any value).
     */
    TOP("top");


    /**
     * The name of the ZeroAbstractValue.
     */
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

    ZeroAbstractValue(String name) {
        this.name = name;
    }

    /**
     * Returns the result of the addition between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the addition.
     */
    public ZeroAbstractValue add(ZeroAbstractValue another) {
        if (this.equals(BOTTOM) || another.equals(BOTTOM)) {
            return BOTTOM;
        }
        else if (this.equals(another)) {
            return this;
        }
        else if (this.equals(ZERO)) {
            return another;
        }
        else if (another.equals(ZERO)) {
            return this;
        }
        else {
            return TOP;
        }
    }

    /**
     * Returns the result of the division between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the division.
     */
    public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
        if (this.equals(BOTTOM) || another.equals(BOTTOM)) {
            return BOTTOM;
        }
        else if (another.equals(ZERO)) {
            return BOTTOM;
        }
        else if (this.equals(ZERO)) {
            return ZERO;
        }
        else {
            return TOP;
        }
    }

    /**
     * Returns the result of the multiplication between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the multiplication.
     */
    public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
        if (this.equals(BOTTOM) || another.equals(BOTTOM)) {
            return BOTTOM;
        }
        else if (this.equals(ZERO) || another.equals(ZERO)) {
            return ZERO;
        }
        else if (this.equals(another) && (!this.equals(TOP))) {
            return POSITIVE;
        }
        else if ((this.equals(POSITIVE) && another.equals(NEGATIVE)) ||
                 (this.equals(NEGATIVE) && another.equals(POSITIVE))) {
            return NEGATIVE;
        }
        else {
            return TOP;
        }
    }

    /**
     * Returns the result of the subtraction between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the subtraction.
     */
    public ZeroAbstractValue substract(ZeroAbstractValue another) {
        if (this.equals(BOTTOM) || another.equals(BOTTOM)) {
            return BOTTOM;
        }
        else if (this.equals(ZERO) && another.equals(ZERO)) {
            return ZERO;
        }
        else if ((this.equals(NEGATIVE) || this.equals(ZERO)) && (another.equals(ZERO) || another.equals(POSITIVE))) {
            return NEGATIVE;
        }
        else if ((this.equals(POSITIVE) || this.equals(ZERO)) && (another.equals(ZERO) || another.equals(NEGATIVE))) {
            return POSITIVE;
        }
        else {
            return TOP;
        }
    }

    /**
     * Returns the result of the merge between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the merge.
     */
    public ZeroAbstractValue merge(ZeroAbstractValue another) {
        if (this.equals(BOTTOM)) {
            return another;
        }
        else if (another.equals(BOTTOM)) {
            return this;
        }
        else if (this.equals(another)) {
            return this;
        }
        else {
            return TOP;
        }
    }

}
