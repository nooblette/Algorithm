class Solution {
    static class BinaryHeap {
        // 힙의 엘리먼트를 담아둘 리스트 변수
        public List<Integer> elems;
        
        public BinaryHeap() {
            // 클래스 생성시 빈 리스트로 선언, 첫 번째 값은 null을 삽입하여 사용하지 않음(1번 인덱스부터 사용)
                elems = new ArrayList<>();
                elems.add(null);
        }
        
        // 두 엘리먼트 값을 교체
        public void swap(int i, int j) {
            int temp = elems.get(i);
            elems.set(i, elems.get(j));
            elems.set(j, temp);
        }
        
        public void percolateUp() {
            // 마지막 엘리먼트 위치, 가장 직전에 삽입한 값이 위치한 인덱스를 추출한다.
            int idx = elems.size() - 1;
            
            // 부모 노드의 인덱스
            int parentIdx = idx / 2;
            
            // 부모 노드가 존재한다면 계속 반복
            while(parentIdx > 0) {
                // 부모 노드가 더 작으면 스왑
                if (elems.get(idx) > elems.get(parentIdx)) {
                    swap(idx, parentIdx);
                }
                
                // 현재 엘리먼트 값을 부모노드로 지정
                idx = parentIdx;
                
                // 현재 엘리먼트의 부모 노드 지정
                parentIdx = idx / 2;
            }
        }
        
        public void insert(int k) {
            // 신규 엘리먼트 추가
            elems.add(k);
            
            // 업힙 연산 수행
            percolateUp();
        }

        public void maxHeapify(int i) {
            // 왼쪽 자식 인덱스
            int left = i * 2;
            
            // 오른쪽 자식 인덱스
            int right = i * 2 + 1;
            
            // 현재 노드의 값을 가장 큰 값으로 가정
            int largest = i;
            
            // 왼쪽 자식 노드가 존재하고 현재 노드의 값보다 더 크다면 가장 큰 값은 왼쪽 자식 노드로 선언
            if(left <= elems.size() - 1 && elems.get(left) > elems.get(largest))
                largest = left;
            // 오른쪽 자식 노드가 존재하고 현재 노드의 값보다 더 크다면 가장 큰 값은 오른쪽 자식 노드로 선언
            if(right <= elems.size() - 1 && elems.get(right) > elems.get(largest))
                largest = right;
            // 가장 큰 값이 현재 노드가 아니라면 값 스왑 진행
            if(largest != i) {
                // 가장 큰 노드와 현재 노드의 값 스왑
                swap(largest, i);
                
                // 스왑 이후 계속 진행
                maxHeapify(largest);
            }
        }
        
        public int extract() {
            // 루트 값 추출, 최소 힙이므로 루트가 가장 작은 값이 온다.
            int extracted = elems.get(1);
            
            // 루트에 마지막 엘리먼트 삽입
            elems.set(1, elems.get(elems.size() - 1));
            
            // 마지막 엘리먼트 제거
            elems.remove(elems.size() - 1);
            
            // 루트에 대해 다운힙 연산 수행
            maxHeapify(1);
            
            // 추출한 기존 루트 값 반환
            return extracted;
        }
    }

    public int findKthLargest(int[] nums, int k) {
        // 힙 선언
        BinaryHeap heap = new BinaryHeap();
        
        // 입력값을 모두 힙에 삽입한다.
        for(int num : nums) {
            heap.insert(num);
        }
        
        // k - 1만큼 힙에서 추출한다.
        for(int i = 0; i < k - 1; i++)
            heap.extract();
            
        // k번째 마지막 값을 정답으로 반환
        return heap.extract();
    }
}