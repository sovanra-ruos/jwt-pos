package co.itc.pos.features.report;

import co.itc.pos.features.report.dto.ReportResponse;

public interface ReportService {
    ReportResponse getSalesReport();
    ReportResponse getInventoryReport();
}
