var gulp = require('gulp');

var awspublish = require('gulp-awspublish');
var config = require('./config');
gulp.task('deploy', function() {
  var publisher = awspublish.create(config.aws);
  var headers = {};
  return gulp.src(['./dst/**/**', '!./dst/node_modules/**/**'])
    .pipe(publisher.publish(headers))
    .pipe(publisher.cache())
    .pipe(awspublish.reporter());
});

