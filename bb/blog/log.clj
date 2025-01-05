(ns blog.log)

(defn log [& args]
  (apply println args))
