package orders

/**
 * Applies a percentage discount to every product in the order.
 *
 * Hint: use [products] to read the current list, [removeProductById] and
 * [addProduct] to replace each product with a discounted copy.
 * Use [Product.copy] to create a new product with a modified price.
 *
 * @param discountPercent discount percentage (e.g. 10 means 10%)
 * @param logger optional callback invoked with a log message for each product
 */
fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    val discountPercentDouble = (1.0 - discountPercent / 100.0)
    products.forEach {
        removeProductById(it.id)
        logger?.invoke("Removed Product ${it.id}")
        addProduct(it.copy(price = (it.price * discountPercentDouble).toInt()))
        logger?.invoke("Added Product ${it.id}")
    }
}
