package co.itc.pos.features.report;

import co.itc.pos.domain.Inventory;
import co.itc.pos.domain.Sale;
import co.itc.pos.features.Sale.SaleRepository;
import co.itc.pos.features.inventory.InventoryRepository;
import co.itc.pos.features.report.dto.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final InventoryRepository inventoryRepository;
    private final SaleRepository saleRepository;


    @Override
    public ReportResponse getSalesReport() {
        List<Sale> sales = saleRepository.findAll();

        return ReportResponse.builder()
                .reportName("Sales Report")
                .data(sales)
                .build();
    }

    @Override
    public ReportResponse getInventoryReport() {

        List<Inventory> inventories = inventoryRepository.findAll();

        return ReportResponse.builder()
                .reportName("Inventory Report")
                .data(inventories)
                .build();


    }
}
