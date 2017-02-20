// lazyload config

angular.module('app')
    /**
   * jQuery plugin config use ui-jq directive , config the js and css files that required
   * key: function name of the jQuery plugin
   * value: array of the css js file located
   */
  .constant('JQ_CONFIG', {
      easyPieChart:   ['resource/vendor/jquery/charts/easypiechart/jquery.easy-pie-chart.js'],
      sparkline:      ['resource/vendor/jquery/charts/sparkline/jquery.sparkline.min.js'],
      plot:           ['resource/vendor/jquery/charts/flot/jquery.flot.min.js',
                          'resource/vendor/jquery/charts/flot/jquery.flot.resize.js',
                          'resource/vendor/jquery/charts/flot/jquery.flot.tooltip.min.js',
                          'resource/vendor/jquery/charts/flot/jquery.flot.spline.js',
                          'resource/vendor/jquery/charts/flot/jquery.flot.orderBars.js',
                          'resource/vendor/jquery/charts/flot/jquery.flot.pie.min.js'],
      slimScroll:     ['resource/vendor/jquery/slimscroll/jquery.slimscroll.min.js'],
      sortable:       ['resource/vendor/jquery/sortable/jquery.sortable.js'],
      nestable:       ['resource/vendor/jquery/nestable/jquery.nestable.js',
                          'resource/vendor/jquery/nestable/nestable.css'],
      filestyle:      ['resource/vendor/jquery/file/bootstrap-filestyle.min.js'],
      slider:         ['resource/vendor/jquery/slider/bootstrap-slider.js',
                          'resource/vendor/jquery/slider/slider.css'],
      chosen:         ['resource/vendor/jquery/chosen/chosen.jquery.min.js',
                          'resource/vendor/jquery/chosen/chosen.css'],
      TouchSpin:      ['resource/vendor/jquery/spinner/jquery.bootstrap-touchspin.min.js',
                          'resource/vendor/jquery/spinner/jquery.bootstrap-touchspin.css'],
      wysiwyg:        ['resource/vendor/jquery/wysiwyg/bootstrap-wysiwyg.js',
                          'resource/vendor/jquery/wysiwyg/jquery.hotkeys.js'],
      dataTable:      ['resource/vendor/jquery/datatables/jquery.dataTables.min.js',
                          'resource/vendor/jquery/datatables/dataTables.bootstrap.js',
                          'resource/vendor/jquery/datatables/dataTables.bootstrap.css'],
      vectorMap:      ['resource/vendor/jquery/jvectormap/jquery-jvectormap.min.js', 
                          'resource/vendor/jquery/jvectormap/jquery-jvectormap-world-mill-en.js',
                          'resource/vendor/jquery/jvectormap/jquery-jvectormap-us-aea-en.js',
                          'resource/vendor/jquery/jvectormap/jquery-jvectormap.css'],
      footable:       ['resource/vendor/jquery/footable/footable.all.min.js',
                          'resource/vendor/jquery/footable/footable.core.css']
      }
  )
  // oclazyload config
  .config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
          debug:  false,
          events: true,
          modules: [
              {
                  name: 'ngGrid',
                  files: [
                      'resource/vendor/modules/ng-grid/ng-grid.min.js',
                      'resource/vendor/modules/ng-grid/ng-grid.min.css',
                      'resource/vendor/modules/ng-grid/theme.css'
                  ]
              },
              {
                  name: 'ui.select',
                  files: [
                      'resource/vendor/modules/angular-ui-select/select.min.js',
                      'resource/vendor/modules/angular-ui-select/select.min.css'
                  ]
              },
              {
                  name:'angularFileUpload',
                  files: [
                    'resource/vendor/modules/angular-file-upload/angular-file-upload.min.js'
                  ]
              },
              {
                  name:'ui.calendar',
                  files: ['resource/vendor/modules/angular-ui-calendar/calendar.js']
              },
              {
                  name: 'ngImgCrop',
                  files: [
                      'resource/vendor/modules/ngImgCrop/ng-img-crop.js',
                      'resource/vendor/modules/ngImgCrop/ng-img-crop.css'
                  ]
              },
              {
                  name: 'angularBootstrapNavTree',
                  files: [
                      'resource/vendor/modules/angular-bootstrap-nav-tree/abn_tree_directive.js',
                      'resource/vendor/modules/angular-bootstrap-nav-tree/abn_tree.css'
                  ]
              },
              {
                  name: 'toaster',
                  files: [
                      'resource/vendor/modules/angularjs-toaster/toaster.js',
                      'resource/vendor/modules/angularjs-toaster/toaster.css'
                  ]
              },
              {
                  name: 'textAngular',
                  files: [
                      'resource/vendor/modules/textAngular/textAngular-sanitize.min.js',
                      'resource/vendor/modules/textAngular/textAngular.min.js'
                  ]
              },
              {
                  name: 'vr.directives.slider',
                  files: [
                      'resource/vendor/modules/angular-slider/angular-slider.min.js',
                      'resource/vendor/modules/angular-slider/angular-slider.css'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular',
                  files: [
                      'resource/vendor/modules/videogular/videogular.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.controls',
                  files: [
                      'resource/vendor/modules/videogular/plugins/controls.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.buffering',
                  files: [
                      'resource/vendor/modules/videogular/plugins/buffering.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.overlayplay',
                  files: [
                      'resource/vendor/modules/videogular/plugins/overlay-play.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.poster',
                  files: [
                      'resource/vendor/modules/videogular/plugins/poster.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.imaads',
                  files: [
                      'resource/vendor/modules/videogular/plugins/ima-ads.min.js'
                  ]
              }
          ]
      });
  }])
;