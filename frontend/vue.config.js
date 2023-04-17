//cors 방지 
module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080',
            }
        }
    }
}