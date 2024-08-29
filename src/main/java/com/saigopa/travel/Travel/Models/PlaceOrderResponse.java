package com.saigopa.travel.Travel.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PlaceOrderResponse {

    private Boolean isSuccess;
    private Success successResponse;
    private Failure failureResponse;

    public PlaceOrderResponse(Boolean isSuccess, Success successResponse, Failure failureResponse) {
        this.isSuccess = isSuccess;
        this.successResponse = successResponse;
        this.failureResponse = failureResponse;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Success getSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(Success successResponse) {
        this.successResponse = successResponse;
    }

    public Failure getFailureResponse() {
        return failureResponse;
    }

    public void setFailureResponse(Failure failureResponse) {
        this.failureResponse = failureResponse;
    }

    // Success Model Class
    public static class Success {
        @JsonProperty("amount")
        private int amount;

        @JsonProperty("amount_due")
        private int amountDue;

        @JsonProperty("amount_paid")
        private int amountPaid;

        @JsonProperty("attempts")
        private int attempts;

        @JsonProperty("created_at")
        private int createdAt;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("entity")
        private String entity;

        @JsonProperty("id")
        private String id;

        @JsonProperty("notes")
        private List<String> notes;

        @JsonProperty("offer_id")
        private String offerId;

        @JsonProperty("receipt")
        private String receipt;

        @JsonProperty("status")
        private String status;

        // Getters and Setters
        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmountDue() {
            return amountDue;
        }

        public void setAmountDue(int amountDue) {
            this.amountDue = amountDue;
        }

        public int getAmountPaid() {
            return amountPaid;
        }

        public void setAmountPaid(int amountPaid) {
            this.amountPaid = amountPaid;
        }

        public int getAttempts() {
            return attempts;
        }

        public void setAttempts(int attempts) {
            this.attempts = attempts;
        }

        public int getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(int createdAt) {
            this.createdAt = createdAt;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getNotes() {
            return notes;
        }

        public void setNotes(List<String> notes) {
            this.notes = notes;
        }

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public String getReceipt() {
            return receipt;
        }

        public void setReceipt(String receipt) {
            this.receipt = receipt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    // Failure Model Class
    public static class Failure {
        @JsonProperty("error")
        private ErrorDetails error;

        // Getters and Setters
        public ErrorDetails getError() {
            return error;
        }

        public void setError(ErrorDetails error) {
            this.error = error;
        }

        public static class ErrorDetails {
            @JsonProperty("code")
            private String code;

            @JsonProperty("description")
            private String description;

            @JsonProperty("source")
            private String source;

            @JsonProperty("step")
            private String step;

            @JsonProperty("reason")
            private String reason;

            @JsonProperty("metadata")
            private Metadata metadata;

            @JsonProperty("field")
            private String field;

            // Getters and Setters
            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getStep() {
                return step;
            }

            public void setStep(String step) {
                this.step = step;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public Metadata getMetadata() {
                return metadata;
            }

            public void setMetadata(Metadata metadata) {
                this.metadata = metadata;
            }

            public String getField() {
                return field;
            }

            public void setField(String field) {
                this.field = field;
            }

            // Metadata class
            public static class Metadata {
                // Metadata can be customized according to API needs
            }
        }
    }
}

