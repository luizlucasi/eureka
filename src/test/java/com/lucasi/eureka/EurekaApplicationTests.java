
/**
     * Retorna o conteúdo como uma data no formato ISO8601.
     * @return uma data no formato ISO8601, ou null se o conteúdo não for uma data.
     */
    public String getDateTimeIso8601() {
        for (Object obj : content) {
            if (obj instanceof String) {
                String str = (String) obj;
                try {
                    DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
                    df.setTimeZone(TimeZone.getTimeZone("GMT"));
                    Date date = df.parse(str);
                    df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    df.setTimeZone(TimeZone.getDefault());
                    return df.format(date);
                } catch (ParseException e) {
                    // não é uma data no formato ISO8601
                }
            }
        }
        return null;
    }
