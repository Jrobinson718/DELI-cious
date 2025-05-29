package com.pluralsight;
/**
 * Represents an abstract base class for predefined, signature sandwiches.
 * This class extends {@link Sandwich} and provides a structure for creating
 * specific sandwich types (like BLT, Philly Cheesesteak) with a fixed set of
 * initial toppings and configurations.
 * <p>
 * Concrete subclasses are responsible for implementing the
 * {@link #applySignatureToppings()} method to define their unique composition.
 * </p>
 */
public abstract class SignatureSandwich extends Sandwich{
    //          === Instance Variables ===
    protected String signatureName;

    //          === Protected Constructor ===
    /**
     * Constructs a new SignatureSandwich with a specified name, size, and bread type.
     * <p>
     * The provided {@code size} and {@code breadType} are used to initialize the
     * underlying {@link Sandwich}. The {@code signatureName} is stored to identify
     * the type of signature sandwich.
     * </p>
     * After basic initialization using the superclass constructor, this constructor
     * calls the {@link #applySignatureToppings()} method. Concrete subclasses must
     * implement {@code applySignatureToppings()} to add the specific toppings and
     * configurations that define that particular signature sandwich.
     *
     * @param signatureName The distinctive name for this signature sandwich (e.g., "BLT").
     * @param size          The initial size for the signature sandwich (e.g., "8", "Large").
     * @param breadType     The initial bread type for the signature sandwich (e.g., "White", "Wheat").
     */
    protected SignatureSandwich(String signatureName, String size, String breadType) {
        super(size, breadType);
        this.signatureName = signatureName;
        setName(signatureName);
        applySignatureToppings();
    }

    //          === Methods ===
    /**
     * Abstract method that must be implemented by concrete subclasses to define
     * and apply the specific set of toppings, sauces, and other configurations
     * (like toasting) that characterize that particular signature sandwich.
     * This method is called during the construction of a {@code SignatureSandwich}.
     */
    protected abstract void applySignatureToppings();
}
