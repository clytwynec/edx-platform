guard {
  build('test-job-one')
} rescue {
  System.exit(0)
}
