[condition][]The product "{product}" gains focus more than {times} in {minutes} minutes=$list: List( $n: size == {times} ) from accumulate( $visit: ProductFocusGainedEvent(product.category == "{product}")over window:time({minutes}m) from entry-point "visit-product-stream",collectList($visit))
[condition][]The product "{product}" gains focus -> {var}={var}:ProductFocusGainedEvent(product.category == "{product}")  from entry-point "visit-product-stream"
[condition][]The product "{product}" gains focus {seconds} seconds after {productB} -> {var}={var}: ProductFocusGainedEvent(product.category == "{product}", this after [0s,{seconds}s] {productB}) from entry-point "visit-product-stream"
[condition][]The product "{product}" gains focus=$focusGained: ProductFocusGainedEvent(product.category == "{product}")  from entry-point "visit-product-stream"
[condition][]The product "{product}" doesn't loose the focus for {seconds} seconds=not( ProductFocusLostEvent(product.category == "{product}", this  after [0s,{seconds}s] $focusGained)  from entry-point "visit-product-stream")
[condition][]There is a COMBO EVENT=$combo: VisitComboEvent() from entry-point "buy-product-stream"
[condition][]There is a VISIT THRESHOLD REACHED EVENT -> {var}={var}: VisitThresholdReachedEvent($amount: amount) from entry-point "buy-product-stream"
[condition][]There is no BUY EVENT {seconds} seconds after {var}=not( BuyProductEvent(this after[0s,{seconds}s] {var})  )
[condition][]There is a BUY EVENT {seconds} seconds after {var}=BuyProductEvent(this after[0s,{seconds}s] {var}) from entry-point "buy-product-stream"
[condition][]AND=and

[consequence][]Add notification: "{notification}"=notifications.setText("{notification}"); 
[consequence][]Add notification about COMBO EVENT SIZE=notifications.setText("Products in the combo: "+$combo.getProducts().size()); 

[consequence][]Add a VISIT THRESHOLD REACHED EVENT for the product=kcontext.getKnowledgeRuntime().getWorkingMemoryEntryPoint("buy-product-stream").insert(new VisitThresholdReachedEvent($n, ((ProductFocusGainedEvent)$list.get(0)).getProduct()));
[consequence][]Create a new Product list -> {var}=List {var} = new ArrayList();
[consequence][]Add product {var} to {productList}={productList}.add({var});
[consequence][]Add {var} as a COMBO EVENT=kcontext.getKnowledgeRuntime().getWorkingMemoryEntryPoint("buy-product-stream").insert(new VisitComboEvent({var}));
[consequence][]Add a new SWING COMPONENT with title "{title}" for {var} in <{x}> <{y}>=SwingVisualComponent product4Component = new SwingVisualComponent("{title}" + {var} , {x}, {y}); insert(product4Component); panel.add(product4Component.getFrame(), javax.swing.JLayeredPane.DEFAULT_LAYER);