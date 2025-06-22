package com.saigopa.travel.Travel.Routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;

public class yt {
@PostMapping("/bulk-print")
public ResponseEntity<Map<String, Object>> generateWaybillPrint(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
    Map<String, Object> data = new HashMap<>();
    try {
        // Extract waybillIds from requestBody (ensure type safety)
        List<String> waybillIds = (List<String>) requestBody.getOrDefault("waybillIds", new ArrayList<>());
        // Fetch depotId from session
        long depotId = Long.parseLong(request.getSession().getAttribute("depotId").toString());
        EntityObj depot = entityObjService.getReferenceById(depotId);
        // List to store waybill content
        List<String> waybillContents = new ArrayList<>();
        // Single StringBuilder to reuse across all waybills
        StringBuilder paginatedWaybillContent = new StringBuilder();
        // Loop through each waybillId
        for (String waybillIdString : waybillIds) {
            Long waybillId = Long.valueOf(waybillIdString);
            Waybill waybill = waybillservice.getReferenceById(waybillId);
            List<WaybillDevice> devices = waybillDeviceService.findAllByWaybillId(waybillId);
            List<Map<String, Object>> bags = waybillDeviceService.findAllByBagNo(waybillId, depotId);
            // Generate the text content for each waybill
            String textContent = waybillDeviceService.generateTextContent(waybill, devices, bags, depot.getEntityName());
            String ticketInfo = String.valueOf(waybillDeviceService.getTicketInfo(bags));
            String footer = String.valueOf(waybillDeviceService.getFooter());
            // Combine the contents for the current waybill
            String waybillContent = textContent + ticketInfo + footer;
            // Pagination logic for the combined content
            String[] lines = waybillContent.split("\n");
            int totalLines = lines.length;
            int linesPerPage = 65;
            int currentLine = 0;
            paginatedWaybillContent.setLength(0);  // Reset the StringBuilder for the new waybill
            // Paginate the content for the current waybill
            while (currentLine < totalLines) {
                for (int linesOnCurrentPage = 0; linesOnCurrentPage < linesPerPage && currentLine < totalLines; linesOnCurrentPage++) {
                    paginatedWaybillContent.append(lines[currentLine++]).append("\n");
                }
                // Add form feed for page break if more lines exist
                if (currentLine < totalLines) {
                    paginatedWaybillContent.append("\n\n\n\n\n\n");
                }
            }
            // Ensure the next waybill starts on a new page by filling incomplete pages with blank lines
            int linesPrintedOnLastPage = currentLine % linesPerPage;
            if (linesPrintedOnLastPage != 0) {
                paginatedWaybillContent.append("\n".repeat(linesPerPage - linesPrintedOnLastPage));
            }
            // Add the paginated content for this waybill to the list
            waybillContents.add(paginatedWaybillContent.toString());
        }
        // Send the content of all waybills as a response
        data.put("status", true);
        data.put("waybills", waybillContents);
        return ResponseEntity.ok(data);
    } catch (Exception e) {
        e.printStackTrace();
        data.put("status", false);
        data.put("message", "Failed to generate waybill content: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
    }
}
}
