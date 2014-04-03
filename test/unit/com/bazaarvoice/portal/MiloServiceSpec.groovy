package com.bazaarvoice.portal

import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 *
 * NOTE: do not use setUp/tearDown or @Before/@After
 *     They are not guaranteed to run after Grails Test Mixin setUp/tearDown, and we often need them to.
 *     See http://grails.1312388.n4.nabble.com/Test-mixins-and-Before-td4329905.html for details
 */
@TestFor(MiloService)
class MiloServiceSpec {

    void testProductData() {
        float austinLat = 30.354675
        float austinLng = -97.7340854
        service.getProductData("tv", austinLat, austinLng)
    }

    void testProductAvail() {
        float austinLat = 30.354675
        float austinLng = -97.7340854
        List<String> offerIds = ["29854202"]
        service.getProductAvailability(offerIds, austinLat, austinLng, 1)
    }
}
