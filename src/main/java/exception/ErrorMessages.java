package exception;

public enum ErrorMessages {
    OUT_OF_STOCK("Product out of stock."),
    PRODUCT_NOT_AVAILABLE("Product not available."),
    INSUFFICIENT_BALANCE("Insufficient balance."),
    MANUFACTURE_YEAR_LESS("Product manufacture year lesser than your request.");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
