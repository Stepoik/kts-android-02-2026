package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()

    /** Read-only view of products in the order. */
    val products: List<Product> get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    /**
     * Adds a product to the order.
     * If the product is null, it should be ignored.
     */
    fun addProduct(product: Product?) {
        product ?: return
        _products.add(product)
    }

    /**
     * Removes the first product matching [productId].
     */
    fun removeProductById(productId: Int) {
        val productIndex = _products.indexOfFirst { it.id == productId }
        if (productIndex != -1) {
            _products.removeAt(productIndex)
        }
    }

    /**
     * Returns the total price of all products in the order.
     */
    override fun calculateTotal(): Int {
        return _products.sumOf { it.price }
    }

    /**
     * Marks the order as paid.
     * Throws [IllegalStateException] if the order has no products.
     */
    fun pay() {
        // Также стоит добавить проверку текущего статуса, но в описании к методу такого нет, поэтому не делал
        if (_products.isEmpty()) {
            throw IllegalStateException("Empty product list")
        }
        status = OrderStatus.Paid
    }

    /**
     * Cancels the order with the given reason.
     * If [reason] is null, use "Unknown reason".
     */
    fun cancel(reason: String?) {
        status = OrderStatus.Cancelled(reason ?: UNKNOWN_REASON)
    }

    companion object {
        private const val UNKNOWN_REASON = "Unknown reason"
    }
}
