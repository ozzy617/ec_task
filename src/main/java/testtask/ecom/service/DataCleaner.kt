package testtask.ecom.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import testtask.ecom.entity.Status
import testtask.ecom.repository.OrderRepository

@Component
class DataCleaner(
        private val orderRepository: OrderRepository
) {

    @Scheduled(cron = "0 0 * * * *")
    fun cleanDate() {
        val orders = orderRepository.findByStatus(Status.DONE)
        orders.forEach {
            orderRepository.delete(it)
        }
    }
}